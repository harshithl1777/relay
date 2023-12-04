import { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import {
    Box,
    Button,
    Heading,
    Link,
    Text,
    Image,
    Popover,
    PopoverTrigger,
    PopoverContent,
    PopoverArrow,
    PopoverBody,
    Tooltip,
    IconButton,
    Modal,
    ModalOverlay,
    ModalContent,
    useDisclosure,
} from '@chakra-ui/react';
import { createStandaloneToast } from '@chakra-ui/toast';
import { IoArrowUp, IoArrowDown, IoQrCode } from 'react-icons/io5';
import { updateActiveSessions, startSession, endSession } from 'redux/actions/sessionsActions';
import { refreshClassList } from 'redux/actions/classesActions';
import theme from 'config/theme';
import { Record } from 'components';
import noDataImage from 'assets/images/noData.svg';
import styles from 'containers/Sessions.module.css';
import app from 'config/firebase';
import { doc, getFirestore, onSnapshot } from 'firebase/firestore';
import { retrieveQRCode } from 'utils/models/sessions';
import QRCodeModal from 'containers/QRCodeModal';

const Sessions = (props) => {
    const { auth, classes, sessions, realtime, updateActiveSessions, startSession, endSession, refreshClassList } = props;
    const [startSessionSubmitted, setStartSessionSubmitted] = useState(false);
    const [endSessionSubmitted, setEndSessionSubmitted] = useState(false);
    const [realtimeSubscribed, setRealtimeSubscribed] = useState(false);
    const [unsubscribe, setUnsubscribe] = useState(null);
    const [qrCodeImageURL, setQRCodeImageURL] = useState(null);

    const { isOpen, onOpen, onClose } = useDisclosure();
    const { toast } = createStandaloneToast({ theme });
    const db = getFirestore(app);

    // Filter through active sessions and find currently selected one
    const selectedActiveSession = sessions.activeSessions.filter(
        (session) => session.courseID === classes.selectedClass.courseID,
    );

    useEffect(() => {
        if (sessions.activeClasses.includes(classes.selectedClass.courseID) && !realtimeSubscribed) {
            const selectedActiveSession = sessions.activeSessions.filter(
                (session) => session.courseID === classes.selectedClass.courseID,
            );
            const unsubscribe = onSnapshot(doc(db, 'sessions', selectedActiveSession[0].id), (doc) => {
                const data = doc.data();
                console.log(data);
                updateActiveSessions(sessions.activeSessions, { ...data, id: doc.id });
            });
            console.log(typeof unsubscribe);
            setUnsubscribe(() => () => unsubscribe());
            setRealtimeSubscribed(true);
        }

        if (selectedActiveSession.length === 1) {
            retrieveQRCodeBlobURL(selectedActiveSession[0].id);
        }
    }, [realtime]);

    const onStartSession = async () => {
        if (sessions.activeSessions.length >= 1) {
            toast({
                title: 'Another attendance session is active',
                description: 'Please end the session before starting another.',
                status: 'error',
                duration: 9000,
                isClosable: true,
                position: 'top-right',
            });
        } else {
            setStartSessionSubmitted(true);
            await startSession(classes.selectedClass.courseID, auth.userID);
            setTimeout(() => {
                setStartSessionSubmitted(false);
            }, 500);
        }
    };

    const onEndSession = async () => {
        unsubscribe();
        setEndSessionSubmitted(true);
        await endSession(selectedActiveSession[0].id, auth.userID);
        await refreshClassList(auth.userID);
        setTimeout(() => {
            setEndSessionSubmitted(false);
        }, 500);
    };

    const retrieveQRCodeBlobURL = async () => {
        const imageURL = await retrieveQRCode(selectedActiveSession[0].id);
        setQRCodeImageURL(imageURL);
    };

    const renderQRCodeModal = () => (
        <Modal isOpen={isOpen} onClose={onClose} size='md' motionPreset='slideInBottom' isCentered>
            <ModalOverlay />
            <ModalContent borderRadius='15px' display='flex' alignItems='center' justifyContent='center'>
                <QRCodeModal qrCodeURL={qrCodeImageURL} onClose={onClose} />
            </ModalContent>
        </Modal>
    );

    const renderActiveSessionsContent = () => {
        if (sessions.activeClasses.includes(classes.selectedClass.courseID)) {
            const attendance = selectedActiveSession[0].attendance;

            // Return attendance list or placeholder text with code
            if (attendance.length >= 1) {
                return (
                    <Box display='flex' flexDirection='column' gap='4px' marginTop='22px'>
                        {attendance.map((student) => (
                            <Record record={student} key={student.studentID} />
                        ))}
                    </Box>
                );
            } else {
                return (
                    <Box width='100%' marginTop='160px' display='flex' flexDirection='column' alignItems='center'>
                        <Button
                            leftIcon={<IoQrCode size={20} />}
                            iconSpacing='10px'
                            bgColor='navy.600'
                            _hover={{ bgColor: 'navy.600' }}
                            size='lg'
                            onClick={onOpen}
                        >
                            View QR Code
                        </Button>
                        <Heading fontSize='xl' marginTop='20px'>
                            Attendance session started..
                        </Heading>
                        <Text width='400px' textAlign='center' marginTop='5px' fontWeight='normal' color='gray.500'>
                            Give the above code to your students and ask them to navigate to{' '}
                            <Link fontWeight='bold' color='cyan.500' href='/students'>
                                relay.netlify.app/students
                            </Link>
                            .
                        </Text>
                    </Box>
                );
            }
        } else {
            return (
                <Box display='flex' flexDirection='column' alignItems='center' width='100%' marginTop='140px'>
                    <Image src={noDataImage} alt='blank clipboards' />
                    <Heading fontSize='xl' marginTop='20px'>
                        No currently active sessions
                    </Heading>
                    <Text width='400px' textAlign='center' marginTop='5px' fontWeight='normal' color='gray.500'>
                        You don’t have any active attendance sessions. Try clicking{' '}
                        <span style={{ fontWeight: 'bold' }}>start sessions</span> to get one going!
                    </Text>
                </Box>
            );
        }
    };

    return (
        <Box width='100%'>
            <Box display='flex' flexDirection='row'>
                <Box>
                    <Box display='flex' flexDirection='row' alignItems='center' gap='10px'>
                        <Heading fontSize='xl' color='navy.600'>
                            Active sessions
                        </Heading>
                        {selectedActiveSession.length >= 1 && (
                            <Box
                                paddingLeft='10px'
                                paddingRight='10px'
                                height='25px'
                                backgroundColor='orange.400'
                                color='white'
                                display='flex'
                                flexDirection='row'
                                alignItems='center'
                                gap='10px'
                                borderRadius='6px'
                                fontSize='xs'
                                fontWeight='semibold'
                            >
                                <div className={styles.pointer} />
                                {selectedActiveSession[0].attendance.length}{' '}
                                {selectedActiveSession[0].attendance.length === 1 ? 'student' : 'students'}
                            </Box>
                        )}
                    </Box>
                    <Text fontSize='sm' color='gray.500'>
                        Ongoing attendance sessions will show up here.
                    </Text>
                </Box>
                <Box margin='auto' marginRight='0px' display='flex' flexDirection='row' gap='10px'>
                    {qrCodeImageURL !== null &&
                        selectedActiveSession.length === 1 &&
                        selectedActiveSession[0].attendance.length !== 0 && (
                            <Tooltip label='View QR Code' placement='bottom' borderRadius='6px'>
                                <IconButton
                                    onClick={onOpen}
                                    bgColor='navy.600'
                                    _hover={{ bgColor: 'navy.600' }}
                                    icon={<IoQrCode size={20} />}
                                ></IconButton>
                            </Tooltip>
                        )}
                    {selectedActiveSession.length >= 1 ? (
                        <Button
                            leftIcon={<IoArrowDown />}
                            variant='primary'
                            backgroundColor='red.500'
                            _hover={{ backgroundColor: 'red.600' }}
                            _loading={{ backgroundColor: 'red.600' }}
                            isLoading={endSessionSubmitted}
                            onClick={onEndSession}
                        >
                            End session
                        </Button>
                    ) : (
                        <Button
                            leftIcon={<IoArrowUp />}
                            variant='primary'
                            backgroundColor='teal.400'
                            _hover={{ backgroundColor: 'teal.500' }}
                            isLoading={startSessionSubmitted}
                            _loading={{ backgroundColor: 'teal.500' }}
                            onClick={onStartSession}
                        >
                            Start session
                        </Button>
                    )}
                </Box>
            </Box>
            {renderActiveSessionsContent()}
            {renderQRCodeModal()}
        </Box>
    );
};

const mapStateToProps = ({ auth, classes, sessions, realtime }) => ({ auth, classes, sessions, realtime });

export default connect(mapStateToProps, { updateActiveSessions, startSession, endSession, refreshClassList })(Sessions);
