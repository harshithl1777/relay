import APICore from 'utils/api/utils/core';

const url = 'sessions';

const sessionsAPI = new APICore({
    get: false,
    post: true,
    put: false,
    remove: true,
    url: url,
});

export default sessionsAPI;
