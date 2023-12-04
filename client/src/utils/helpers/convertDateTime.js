import dayjs from 'dayjs';
import calendar from 'dayjs/plugin/calendar';

const convertDateTime = (datetime) => {
    dayjs.extend(calendar);
    console.log(datetime);
    return dayjs.unix(datetime.seconds).calendar(null, {
        sameDay: '[Today], h:mm A',
        lastDay: '[Yesterday], h:mm A',
        lastWeek: '[Last] dddd, h:mm A',
        sameElse: 'MMM D, h:mm A',
    });
};

export default convertDateTime;
