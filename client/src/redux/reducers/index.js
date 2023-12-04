import authReducer from 'redux/reducers/authReducer';
import classesReducer from 'redux/reducers/classesReducer';
import sessionsReducer from 'redux/reducers/sessionsReducer';
import realtimeReducer from 'redux/reducers/realtimeReducer';

const rootReducer = {
    auth: authReducer,
    classes: classesReducer,
    sessions: sessionsReducer,
    realtime: realtimeReducer,
};

export default rootReducer;
