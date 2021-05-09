const { Client } = require('whatsapp-web.js');
const Discord = require('discord.js');
const QRCode = require('qrcode');
const fs = require('fs');
const axios = require('axios');

const client = fs.existsSync('./sessions.json')
    ? new Client({ session: require('../sessions.json') })
    : new Client();

const discordClient = new Discord.Client();

client.on('qr', async (qr) => {
    await QRCode.toFile('./qrcode.png', qr);

    discordClient.users.fetch(process.env.DISCORD_USER_ID).then((user) => {
        const authEmbed = new Discord.MessageEmbed()
            .setColor('#7289DA')
            .setTitle('Authentication Required')
            .setDescription('Please scan this QR Code with WhatsApp')
            .setTimestamp()
            .setImage('./qrcode.png')
            .setFooter('WhatsBot Copyright © Harshith Latchupatula');
        user.send(authEmbed);
        fs.unlink('./qrCode.png');
    });
});

client.on('authenticated', (session) => {
    fs.writeFile('./sessions.json', JSON.stringify(session), (err) => {
        if (err) throw err;
    });
});

client.on('message', (message) => {
    discordClient.users
        .fetch(process.env.DISCORD_USER_ID)
        .then(async (user) => {
            const contact = await message.getContact();
            user.send(`${contact}: ${message.body}`);
        });
});

const sendMessage = (msg, client) => {
    if ((msg.channel.type = 'dm')) {
    }
};

client.initialize();
discordClient.login(process.env.DISCORD_BOT_TOKEN);

module.exports = {
    sendMessage,
};
