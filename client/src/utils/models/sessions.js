import { collection, query, where, getDocs, doc, getDoc, getFirestore } from 'firebase/firestore';
import { getBlob, getStorage, ref } from 'firebase/storage';
import app from 'config/firebase';
import { sessionsAPI } from 'utils/api';

const db = getFirestore(app);

export const retrieveSessions = async (instructorID) => {
    const sessionQuery = query(collection(db, 'sessions'), where('instructorID', '==', instructorID));
    const querySnapshot = await getDocs(sessionQuery);
    const sessions = [];
    querySnapshot.forEach((doc) => sessions.push({ id: doc.id, ...doc.data() }));
    return sessions;
};

export const retrieveSessionsByCode = async (sessionCode) => {
    const sessionQuery = query(collection(db, 'sessions'), where('sessionCode', '==', sessionCode));
    const querySnapshot = await getDocs(sessionQuery);
    const sessions = [];
    querySnapshot.forEach((doc) => sessions.push({ id: doc.id, ...doc.data() }));
    return sessions;
};

export const retrieveSessionByID = async (sessionID) => {
    const sessionRef = doc(db, 'sessions', sessionID);
    const session = await getDoc(sessionRef);
    return session.data();
};

export const retrieveQRCode = async (sessionID) => {
    const storage = getStorage(app);
    const pathReference = ref(storage, sessionID + '.png');
    const qrCodeImage = await getBlob(pathReference);
    return URL.createObjectURL(qrCodeImage);
};

export const retrieveClassByID = async (classID) => {
    const classRef = doc(db, 'courses', classID);
    const classObj = await getDoc(classRef);
    return classObj.data();
};

export const addSessionAttendanceRecord = async (
    studentFirstName,
    studentLastName,
    studentEmailAddress,
    studentID,
    sessionID,
) => {
    console.log('HERE');
    const response = await sessionsAPI.post(
        { studentFirstName, studentLastName, studentEmailAddress, studentID },
        {},
        `${sessionID}/attendance`,
    );
    console.log(response);
};

window.addEventListener('beforeunload', () => {});
