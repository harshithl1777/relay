import { connect } from 'react-redux';
import { Box, Heading, Text, Image } from '@chakra-ui/react';
import dayjs from 'dayjs';
import { Record } from 'components';
import { convertDateTimeString } from 'utils/helpers';
import noDataImage from 'assets/images/noData.svg';

const History = (props) => {
    const { classes } = props;
    const classTotalHistory = classes.classList.filter(
        (singleClass) => singleClass.courseID === classes.selectedClass.courseID,
    )[0].history;

    const segregateHistoryByDay = () => {
        let currentSegregator = dayjs(classTotalHistory[0].createdAt).format('DD-MM-YYYY');
        console.log(currentSegregator);
        const segregatedHistory = [[classTotalHistory[0].createdAt]];
        classTotalHistory.forEach((record) => {
            const formattedDate = dayjs(record.createdAt).format('DD-MM-YYYY');
            const recordClone = { ...record };
            if (formattedDate === currentSegregator) {
                segregatedHistory[segregatedHistory.length - 1].push(recordClone);
            } else {
                currentSegregator = formattedDate;
                segregatedHistory.push([recordClone.createdAt, recordClone]);
            }
        });
        return segregatedHistory;
    };

    const renderHistory = () => {
        const selectedClassHistory = segregateHistoryByDay();
        return selectedClassHistory.reverse().map((historyRecord, index) => (
            <Box display='flex' flexDirection='column' gap='35px' marginTop={index !== 0 && '31px'} key={historyRecord[0]}>
                <Box>
                    <Heading fontSize='lg' color='navy.600'>
                        {convertDateTimeString(historyRecord[0])}
                    </Heading>
                    <Text fontSize='sm' color='gray.500'>
                        On this day, {historyRecord.length - 1} {historyRecord.length === 2 ? 'student' : 'students'} came to
                        class.
                    </Text>
                    <Box display='flex' flexDirection='column' gap='4px' marginTop='14px'>
                        {historyRecord.slice(1).map((student) => {
                            let studentClone = { ...student };
                            return <Record record={student} key={student.studentID} />;
                        })}
                    </Box>
                </Box>
            </Box>
        ));
    };

    return classTotalHistory.length >= 1 ? (
        renderHistory()
    ) : (
        <Box display='flex' flexDirection='column' alignItems='center' width='100%' marginTop='140px'>
            <Image src={noDataImage} alt='blank clipboards' />
            <Heading fontSize='xl' marginTop='20px'>
                No history found
            </Heading>
            <Text width='400px' textAlign='center' marginTop='5px' fontWeight='normal' color='gray.500'>
                You don’t have any existing attendance history. Try <span style={{ fontWeight: 'bold' }}>starting a session</span>{' '}
                to see some appear!
            </Text>
        </Box>
    );
};

const mapStateToProps = ({ auth, classes }) => ({ auth, classes });

export default connect(mapStateToProps, {})(History);
