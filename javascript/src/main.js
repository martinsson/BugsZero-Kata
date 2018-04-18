"use strict"

let gameRunner = require('./game-runner')

gameRunner(function (maxInt) {
    return Math.floor(Math.random() * maxInt) + 1
});