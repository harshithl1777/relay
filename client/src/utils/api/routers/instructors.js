import APICore from 'utils/api/utils/core';

const url = 'instructors';

const instructorsAPI = new APICore({
    get: true,
    post: true,
    put: false,
    remove: false,
    url: url,
});

export default instructorsAPI;
