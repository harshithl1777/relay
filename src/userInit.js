const initializeUser = (msg) => {
    if (msg.content === 'w!init' && !msg.author.bot) {
        console.log(msg.author.id);
        msg.author.send(`WhatsBot initialized for user ${msg.author.tag}`);
    }
};

module.exports = {
    initializeUser,
};
