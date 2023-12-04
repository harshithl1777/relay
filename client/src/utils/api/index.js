export { default as usersAPI } from 'utils/api/routers/instructors';
export { default as sessionsAPI } from 'utils/api/routers/sessions';
export { default as coursesAPI } from 'utils/api/routers/courses';

const defaultImportError = () => {
    console.error('API imported as default instead of using destructuring. Import with this import { X } from ...');
};

export default defaultImportError;
