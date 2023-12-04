import APICore from 'utils/api/utils/core';

const url = 'courses';

const coursesAPI = new APICore({
    get: true,
    post: true,
    put: false,
    remove: false,
    url: url,
});

export default coursesAPI;
