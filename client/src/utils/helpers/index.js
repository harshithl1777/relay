export { default as convertDateTime } from 'utils/helpers/convertDateTime';
export { default as convertDateTimeString } from 'utils/helpers/convertDateTimeString';

const defaultImportError = () => {
    console.error('Hook imported as default instead of using destructuring. Import with this import { X } from ...');
};

export default defaultImportError;
