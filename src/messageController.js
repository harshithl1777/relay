const { initializeUser } = require('./userInit.js');
const { sendMessage } = require('./whatsapp.js');

const messageController = (msg, client) => {
    switch (msg.content) {
        case 'w!init':
            initializeUser(msg);
            break;
        default:
            sendMessage(msg, client);
    }
};

module.exports = messageController;
