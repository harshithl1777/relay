import { connect } from 'react-redux';
import { Navigate } from 'react-router-dom';
import { Button, Box, Heading, Text, Checkbox, Divider } from '@chakra-ui/react';
import { IoLogoFacebook, IoLogoGithub, IoLogoGoogle, IoLogoMicrosoft } from 'react-icons/io5';
import { authWithSocials } from 'redux/actions/authActions';
import { useQuery } from 'utils/hooks';

const AuthModal = (props) => {
    const { auth, authWithSocials } = props;
    const redirectURL = useQuery('redirect') || '/app';

    const onAuthModalSocialsSubmit = async (service) => await authWithSocials(service);

    return auth.isLoggedIn ? (
        <Navigate to={redirectURL} />
    ) : (
        <Box width='420px' display='flex' flexDirection='column' alignItems='center' marginTop='44px'>
            <Heading as='b'>Welcome to Relay</Heading>
            <Text textAlign='center' marginTop='12px'>
                Taking attendance doesn’t have to be so slow and tedious. With Relay, it’s never been easier.
            </Text>
            <Divider marginTop='23px' orientation='horizontal' borderColor='gray.300' />
            <Box display='flex' flexDirection='row' alignItems='center' marginTop='23px' marginBottom='42px' gap='15px'>
                <Button
                    onClick={() => onAuthModalSocialsSubmit('GOOGLE')}
                    leftIcon={<IoLogoGoogle color='white' size={22} />}
                    size='md'
                    colorScheme='red'
                >
                    Google
                </Button>
                <Button
                    onClick={() => onAuthModalSocialsSubmit('FACEBOOK')}
                    leftIcon={<IoLogoFacebook color='white' size={22} />}
                    size='md'
                    backgroundColor='blue.500'
                    _hover={{ backgroundColor: 'blue.600' }}
                >
                    Facebook
                </Button>
                <Button
                    onClick={() => onAuthModalSocialsSubmit('GITHUB')}
                    leftIcon={<IoLogoGithub color='white' size={22} />}
                    size='md'
                    backgroundColor='black'
                    _hover={{ backgroundColor: 'blackAlpha.800' }}
                >
                    Github
                </Button>
            </Box>
        </Box>
    );
};

const mapStateToProps = ({ auth }) => ({ auth });

export default connect(mapStateToProps, { authWithSocials })(AuthModal);
