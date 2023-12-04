import { connect } from 'react-redux';
import { Box, Divider, Image, Stack, Tooltip } from '@chakra-ui/react';
import { logOut } from 'redux/actions/authActions';
import { selectClass } from 'redux/actions/classesActions';
import fadedColors from 'assets/json/fadedColorMappings.json';
import logoDark from 'assets/icons/logo-dark.svg';
import logoutLight from 'assets/icons/logout-light.svg';

const Sidebar = (props) => {
    const { logOut, selectClass, classes } = props;
    const onClassSelect = (index) => selectClass(classes.classList[index]);
    const remainingColors = Object.keys(fadedColors);

    return (
        <Box height='100vh' width='90px' backgroundColor='navy.600' display='flex' flexDirection='column' alignItems='center'>
            <>
                <img style={{ width: '50px', height: '50px', marginTop: '20px' }} src={logoDark} alt='logo for dark mode' />
                <Divider width='55px' marginTop='20px' borderColor='gray.600' />
                <Stack direction='column' spacing='13px'>
                    {classes.classList.map((singleClass, index) => {
                        const classColor = remainingColors.pop();
                        return (
                            <Tooltip
                                label={singleClass.courseName}
                                placement='right'
                                borderRadius='6px'
                                key={singleClass.courseName}
                            >
                                <Box
                                    width='60px'
                                    height='60px'
                                    borderRadius='12px'
                                    backgroundColor={
                                        singleClass.courseID === classes.selectedClass.courseID
                                            ? `${classColor}.500`
                                            : fadedColors[classColor]
                                    }
                                    color={
                                        singleClass.courseID === classes.selectedClass.courseID ? 'white' : `${classColor}.600`
                                    }
                                    display='flex'
                                    alignItems='center'
                                    justifyContent='center'
                                    fontSize='xl'
                                    fontWeight='bold'
                                    marginTop={index === 0 ? `20px` : '0px'}
                                    _hover={{ filter: 'brightness(85%)', cursor: 'pointer' }}
                                    onClick={() => onClassSelect(index)}
                                >
                                    {singleClass.courseName.slice(0, 2).toUpperCase()}
                                </Box>
                            </Tooltip>
                        );
                    })}
                </Stack>
                <Tooltip label='Logout' placement='right' borderRadius='6px'>
                    <Image
                        width='34px'
                        height='34px'
                        margin='auto'
                        marginBottom='25px'
                        _hover={{ filter: 'brightness(85%)', cursor: 'pointer' }}
                        onClick={() => logOut()}
                        src={logoutLight}
                        alt='white log out icon'
                    />
                </Tooltip>
            </>
        </Box>
    );
};

const mapStateToProps = ({ classes }) => ({ classes });

export default connect(mapStateToProps, { logOut, selectClass })(Sidebar);
