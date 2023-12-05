import axios from 'axios';
import { getAuth } from 'firebase/auth';
import { handleResponse, handleError } from 'utils/api/utils/response';
import app from 'config/firebase';

const BASE_URL = process.env.REACT_APP_BASE_API_URL || 'http://localhost:8080/api';

axios.interceptors.request.use(async (config) => {
    const url = config.url;
    if (!url.includes('attendance')) {
        const auth = getAuth(app);
        const currentUser = auth.currentUser;
        const socialToken = await currentUser.getIdToken();
        config.headers = { Authorization: `Bearer ${socialToken}` };
    }
    return config;
});

class APICore {
    constructor(options) {
        if (options.get) {
            this.get = async (suffix = '', headers = {}, params = {}) => {
                try {
                    const urlSuffix = suffix === '' ? '' : `/${suffix}`;
                    const response = await axios.get(`${BASE_URL}/${options.url + urlSuffix}`, {
                        params,
                        headers,
                    });
                    return handleResponse(response);
                } catch (error) {
                    return handleError(error);
                }
            };
        }

        if (options.post) {
            this.post = async (body = {}, headers = {}, suffix = '') => {
                const urlSuffix = suffix === '' ? '' : `/${suffix}`;
                try {
                    const response = await axios.post(`${BASE_URL}/${options.url + urlSuffix}`, body, { headers });
                    return handleResponse(response);
                } catch (error) {
                    console.log(error);
                    return handleError(error);
                }
            };
        }

        if (options.put) {
            this.put = async (body = {}, headers = {}, suffix = '') => {
                try {
                    const urlSuffix = suffix === '' ? '' : `/${suffix}`;
                    const response = await axios.put(`${BASE_URL}/${options.url + urlSuffix}`, body, { headers });
                    return handleResponse(response);
                } catch (error) {
                    return handleError(error);
                }
            };
        }

        if (options.patch) {
            this.patch = async (body = {}, headers = {}, suffix = '') => {
                try {
                    const urlSuffix = suffix === '' ? '' : `/${suffix}`;
                    const response = await axios.patch(`${BASE_URL}/${options.url + urlSuffix}`, body, { headers });
                    return handleResponse(response);
                } catch (error) {
                    return handleError(error);
                }
            };
        }

        if (options.remove) {
            this.remove = async (suffix = '', headers = {}) => {
                try {
                    const urlSuffix = suffix === '' ? '' : `/${suffix}`;
                    const response = await axios.delete(`${BASE_URL}/${options.url + urlSuffix}`, { headers });
                    return handleResponse(response);
                } catch (error) {
                    return handleError(error);
                }
            };
        }
    }
}

export default APICore;
