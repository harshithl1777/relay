const Discord = require('discord.js');
const client = new Discord.Client();
require('dotenv').config();
const messageController = require('./messageController.js');

client.once('ready', () => {
    console.log('---- WhatsBot online ----');
});

client.on('message', (msg) => {
    if (!msg.author.bot) messageController(msg, client);
});

client.login(process.env.DISCORD_BOT_TOKEN);
