import { LOAD_ACTIVE_SESSIONS, UPDATE_ACTIVE_SESSIONS, START_SESSION, END_SESSION } from 'redux/actions/types';
import sessionsAPI from 'utils/api/routers/sessions';
import { retrieveSessions } from 'utils/models/sessions';

export const loadActiveSessions = (email) => async (dispatch) => {
    const activeSessions = await retrieveSessions(email);
    const activeClasses = activeSessions.map((session) => session.courseID);
    const payload = {
        activeSessions,
        activeClasses,
    };
    dispatch({ type: LOAD_ACTIVE_SESSIONS, success: true, payload });
};

export const updateActiveSessions = (currentSessions, updatedSession) => (dispatch) => {
    const updatedActiveSessions = [];
    currentSessions.forEach((session) => {
        if (session.id === updatedSession.id) {
            updatedActiveSessions.push(updatedSession);
        } else {
            updatedActiveSessions.push(session);
        }
    });
    const payload = {
        activeSessions: updatedActiveSessions,
    };
    dispatch({ type: UPDATE_ACTIVE_SESSIONS, success: true, payload });
};

export const startSession = (courseID, instructorID) => async (dispatch) => {
    await sessionsAPI.post({ courseID, instructorID });
    const activeSessions = await retrieveSessions(instructorID);
    const activeClasses = activeSessions.map((session) => session.courseID);
    const payload = {
        activeSessions,
        activeClasses,
    };
    dispatch({ type: START_SESSION, success: true, payload });
};

export const endSession = (sessionID, instructorID) => async (dispatch) => {
    await sessionsAPI.remove(sessionID);
    const activeSessions = await retrieveSessions(instructorID);
    const activeClasses = activeSessions.map((session) => session.courseID);
    const payload = {
        activeSessions,
        activeClasses,
    };
    console.log('COMPLETED END', payload);
    dispatch({ type: END_SESSION, success: true, payload });
};
