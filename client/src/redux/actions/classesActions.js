import { createStandaloneToast } from '@chakra-ui/toast';
import { LOAD_CLASS_LIST, REFRESH_CLASS_LIST, SELECT_CLASS, CREATE_NEW_CLASS } from 'redux/actions/types';
import theme from 'config/theme';
import instructorsAPI from 'utils/api/routers/instructors';
import coursesAPI from 'utils/api/routers/courses';
import dayjs from 'dayjs';

const { toast } = createStandaloneToast({ theme });

export const loadClassList = (instructorID) => async (dispatch) => {
    const response = await instructorsAPI.get(`${instructorID}/courses`);
    const classList = response.payload.courses;
    const payload = {
        classList,
        selectedClass: classList[0],
    };
    dispatch({ type: LOAD_CLASS_LIST, success: true, payload });
};

export const selectClass = (selectedClass) => (dispatch) => {
    dispatch({ type: SELECT_CLASS, success: true, payload: { selectedClass } });
};

export const newClass = (instructorID, courseName, onClose) => async (dispatch) => {
    const response = await instructorsAPI.get(`${instructorID}/courses`);
    const classList = response.payload.courses;
    if (classList.length < 9) {
        await coursesAPI.post({ courseName, instructorID });
        const response = await instructorsAPI.get(`${instructorID}/courses`);
        const updatedClassList = response.payload.courses;
        dispatch({ type: CREATE_NEW_CLASS, success: true, payload: { updatedClassList } });
        onClose();
        toast({
            title: 'New class created!',
            status: 'success',
            duration: 9000,
            isClosable: true,
            position: 'top-right',
        });
    } else {
        toast({
            title: 'Maximum class limit reached',
            description: "You've reached your maximum class limit of 9.",
            status: 'error',
            duration: 9000,
            isClosable: true,
            position: 'top-right',
        });
    }
};

export const refreshClassList = (instructorID) => async (dispatch) => {
    const response = await instructorsAPI.get(`${instructorID}/courses`);
    const classList = response.payload.courses;
    const payload = {
        classList,
    };
    dispatch({ type: REFRESH_CLASS_LIST, success: true, payload });
};
