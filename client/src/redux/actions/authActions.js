import {
    GoogleAuthProvider,
    GithubAuthProvider,
    signInWithPopup,
    getAuth,
    getAdditionalUserInfo,
    FacebookAuthProvider,
} from 'firebase/auth';
import { createStandaloneToast } from '@chakra-ui/toast';
import theme from 'config/theme';
import { RESTORE_AUTH_SESSION, AUTH_WITH_SOCIALS, LOG_OUT, CANCEL_RESTORE } from 'redux/actions/types';
import instructorsAPI from 'utils/api/routers/instructors';
import coursesAPI from 'utils/api/routers/courses';
import HTTPError from 'utils/helpers/httpError';
import app from 'config/firebase';

const { toast } = createStandaloneToast({ theme });

export const authWithSocials = (service) => async (dispatch) => {
    try {
        let provider;
        switch (service) {
            case 'FACEBOOK':
                provider = new FacebookAuthProvider();
                provider.addScope('email');
                provider.addScope('public_profile');
                break;
            case 'GITHUB':
                provider = new GithubAuthProvider();
                provider.addScope('read:user');
                provider.addScope('user:email');
                break;
            default:
                provider = new GoogleAuthProvider();
                provider.addScope('https://www.googleapis.com/auth/userinfo.email');
                provider.addScope('https://www.googleapis.com/auth/userinfo.profile');
        }

        const auth = getAuth(app);
        const result = await signInWithPopup(auth, provider);
        const currentUser = auth.currentUser;
        const { isNewUser } = getAdditionalUserInfo(result);

        if (isNewUser) {
            const newUserResponse = await instructorsAPI.post({
                firstName: currentUser.displayName.split(' ')[0],
                lastName: currentUser.displayName.split(' ')[1],
                emailAddress: currentUser.email,
            });
            if (!newUserResponse.success) throw new HTTPError(newUserResponse);

            const newClassResponse = await coursesAPI.post({
                courseName: 'Sample Class',
                instructorID: newUserResponse.payload.instructorID,
            });
            if (!newClassResponse.success) throw new HTTPError(newClassResponse);
            const userID = newUserResponse.payload.instructorID;

            const payload = {
                userID: userID,
                email: currentUser.email,
            };
            dispatch({ type: AUTH_WITH_SOCIALS, success: true, payload });
        } else {
            const response = await instructorsAPI.get('', {}, { emailAddress: currentUser.email });
            const userID = response.payload.instructorID;

            const payload = {
                userID: userID,
                email: currentUser.email,
            };
            dispatch({ type: AUTH_WITH_SOCIALS, success: true, payload });
        }
    } catch (error) {
        if (error.code === 'auth/account-exists-with-different-credential') {
            toast({
                title: 'Email already in use',
                description: 'An account exists with a different provider.',
                status: 'error',
                duration: 9000,
                isClosable: true,
                position: 'top-right',
            });
        } else {
            toast({
                title: 'Something went wrong',
                description: 'Unable to complete login / signup process.',
                status: 'error',
                duration: 9000,
                isClosable: true,
                position: 'top-right',
            });
        }
    }
};

export const restoreAuthSession = (user) => async (dispatch) => {
    const response = await instructorsAPI.get('', {}, { emailAddress: user.email });
    const payload = {
        userID: response.payload.instructorID,
        email: user.email,
    };
    if (response.success) {
        return dispatch({ type: RESTORE_AUTH_SESSION, success: true, payload });
    }
    return dispatch({ type: CANCEL_RESTORE, success: null, payload: null });
};

export const logOut = () => async (dispatch) => {
    const auth = getAuth();
    await auth.signOut();
    window.location.href = '/';
    dispatch({ type: LOG_OUT, success: true });
};
