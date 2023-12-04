import { useEffect, useState } from 'react';
import { Box, Heading, Image, Text, Input, Button, Icon } from '@chakra-ui/react';
import { createStandaloneToast } from '@chakra-ui/toast';
import { IoArrowForward, IoLogoGithub, IoLogoDribbble, IoLogoLinkedin } from 'react-icons/io5';
import { retrieveSessionsByCode, retrieveClassByID, addSessionAttendanceRecord } from 'utils/models/sessions';
import theme from 'config/theme';
import logoLight from 'assets/icons/logo-light.svg';
import checkmarkGreen from 'assets/icons/checkmark-green.svg';
import { useQuery } from 'utils/hooks';
import dayjs from 'dayjs';

const StudentsPage = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [studentID, setStudentID] = useState('');
    const [className, setClassName] = useState(null);
    const [sessionID, setSessionID] = useState(null);
    const [submitLoading, setSubmitLoading] = useState(false);
    const [step, setStep] = useState(0);
    const code = useQuery('code');
    const { toast } = createStandaloneToast({ theme });

    useEffect(() => {
        const run = async () => {
            setTimeout(async () => {
                const matchingSession = await retrieveSessionsByCode(code);
                if (matchingSession.length !== 1) {
                    showInvalidCodeToast();
                } else {
                    const matchingClass = await retrieveClassByID(matchingSession[0].courseID);

                    setSessionID(matchingSession[0].id);
                    setClassName(matchingClass.courseName);
                    setStep(0);
                }
            });
        };
        if (step !== 1) run();
    }, []);

    const showInvalidCodeToast = () => {
        setTimeout(() => {
            toast({
                title: 'Invalid code',
                description: 'Your code does not match any sessions.',
                status: 'error',
                duration: 9000,
                isClosable: true,
                position: 'top-right',
            });
        }, 500);
    };

    const submitAttendance = async () => {
        const studentIDRegex = /^[A-Za-z0-9 _@$#./|']{2,30}$/;
        if (firstName.length >= 1 && lastName.length >= 1) {
            if (studentIDRegex.test(studentID)) {
                const matchingSession = await retrieveSessionsByCode(code);
                const matchingRecord = matchingSession[0].attendance.filter((record) => record.studentID === studentID);
                if (matchingRecord.length !== 1) {
                    setSubmitLoading(true);
                    setTimeout(async () => {
                        await addSessionAttendanceRecord(firstName, lastName, 'sample@gmail.com', studentID, sessionID);
                        setSubmitLoading(false);
                        setStep(1);
                    }, 500);
                } else {
                    toast({
                        title: 'Already submitted',
                        description: 'You have already submitted an attendance record!',
                        status: 'error',
                        duration: 9000,
                        isClosable: true,
                        position: 'top-right',
                    });
                }
            } else {
                toast({
                    title: 'Invalid student ID',
                    description: 'Student IDs must be between 3 - 30 characters.',
                    status: 'error',
                    duration: 9000,
                    isClosable: true,
                    position: 'top-right',
                });
            }
        } else {
            toast({
                title: 'Missing name field',
                description: 'Please fill the name field out.',
                status: 'error',
                duration: 9000,
                isClosable: true,
                position: 'top-right',
            });
        }
    };

    const renderCompletedView = () => (
        <>
            <Image src={checkmarkGreen} alt='relay logo' width='65px' height='65px' />
            <Heading fontSize='4xl' color='navy.600' width='540px' textAlign='center' marginTop='32px'>
                Great, your attendance has been submitted!
            </Heading>
            <Text fontSize='md' color='navy.600' width='510px' textAlign='center' marginTop='10px'>
                We’ve noted down that you were in class today and your instructor should receive it momentarily. Come back at your
                next class to submit again!
            </Text>
            <Button
                rightIcon={<IoArrowForward size={20} />}
                width='100%'
                size='lg'
                variant='primary'
                marginTop='25px'
                marginBottom='100px'
                onClick={() => {
                    window.location.href = '/';
                }}
            >
                Back to the home page
            </Button>
        </>
    );

    const renderContent = () => {
        switch (step) {
            case 0:
                return (
                    <>
                        <Image src={logoLight} alt='relay logo' width='75px' height='75px' />
                        <Heading fontSize='4xl' color='navy.600' width='450px' textAlign='center' marginTop='32px'>
                            Attendance for {className}
                        </Heading>
                        <Text fontSize='md' color='navy.600' width='530px' textAlign='center' marginTop='10px'>
                            We’ve found your class, <span style={{ fontWeight: 'bold' }}>{className}</span>! Enter your first and
                            and student ID below to be marked as present in your class’s attendance.
                        </Text>
                        <Box width='100%' display='flex' flexDirection='row' gap='20px'>
                            <Input
                                backgroundColor='gray.100'
                                border='none'
                                color='navy.600'
                                _focusVisible={{ borderColor: 'var(--chakra-colors-cyan-500)' }}
                                size='lg'
                                placeholder='First name'
                                marginTop='25px'
                                fontWeight='medium'
                                _placeholder={{ fontWeight: 'medium' }}
                                onChange={(e) => setFirstName(e.target.value)}
                                value={firstName}
                            ></Input>
                            <Input
                                backgroundColor='gray.100'
                                border='none'
                                color='navy.600'
                                _focusVisible={{ borderColor: 'var(--chakra-colors-cyan-500)' }}
                                size='lg'
                                placeholder='Last name'
                                marginTop='25px'
                                fontWeight='medium'
                                _placeholder={{ fontWeight: 'medium' }}
                                onChange={(e) => setLastName(e.target.value)}
                                value={lastName}
                            ></Input>
                        </Box>
                        <Input
                            backgroundColor='gray.100'
                            border='none'
                            color='navy.600'
                            _focusVisible={{ borderColor: 'var(--chakra-colors-cyan-500)' }}
                            size='lg'
                            placeholder='Student ID'
                            marginTop='16px'
                            fontWeight='medium'
                            _placeholder={{ fontWeight: 'medium' }}
                            onChange={(e) => setStudentID(e.target.value)}
                            value={studentID}
                        ></Input>
                        <Button
                            rightIcon={<IoArrowForward size={20} />}
                            width='100%'
                            size='lg'
                            variant='primary'
                            marginTop='25px'
                            marginBottom='100px'
                            isLoading={submitLoading}
                            onClick={submitAttendance}
                        >
                            Mark me as present
                        </Button>
                    </>
                );
            case 1:
                return renderCompletedView();
            default:
                return null;
        }
    };

    return (
        <Box width='100vw' height='100vh' backgroundColor='white' display='flex' alignItems='center' justifyContent='center'>
            <Box display='flex' flexDirection='column' alignItems='center'>
                {renderContent()}
                <Box
                    position='absolute'
                    bottom='0'
                    display='flex'
                    flexDirection='row'
                    alignItems='center'
                    justifyContent='center'
                    height='80px'
                    width='100%'
                >
                    <Text color='navy.600' fontSize='lg' margin='auto' marginLeft='40px'>
                        Built by <span style={{ fontWeight: 'var(--chakra-fontWeights-black' }}>Harshith Latchupatula.</span>
                    </Text>
                    <Box display='flex' flexDirection='row' alignItems='center' gap='20px' margin='auto' marginRight='30px'>
                        <Icon
                            as={IoLogoDribbble}
                            _hover={{ filter: 'opacity(80%)', cursor: 'pointer' }}
                            color='navy.600'
                            width='24px'
                            height='24px'
                            onClick={() => window.open('https://dribbble.com/harshithl1777')}
                        />
                        <Icon
                            as={IoLogoLinkedin}
                            color='navy.600'
                            _hover={{ filter: 'opacity(80%)', cursor: 'pointer' }}
                            width='24px'
                            height='24px'
                            onClick={() => window.open('https://linkedin.com/in/harshithlatchupatula')}
                        />
                        <Icon
                            as={IoLogoGithub}
                            _hover={{ filter: 'opacity(80%)', cursor: 'pointer' }}
                            color='navy.600'
                            width='24px'
                            height='24px'
                            onClick={() => window.open('https://github.com/harshithl1777')}
                        />
                    </Box>
                </Box>
            </Box>
        </Box>
    );
};

export default StudentsPage;
