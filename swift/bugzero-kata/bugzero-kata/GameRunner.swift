//
//  GameRunner.swift
//  bugzero-kata
//
//  Created by Frédéric Ruaudel on 20/04/2018.
//  Copyright © 2018 Frédéric Ruaudel. All rights reserved.
//

import Foundation

class GameRunner {
    var notAWinner: Bool = false
    var aGame: Game
    
    init() {
        aGame = Game()
        aGame.add("Chet")
        aGame.add("Pat")
        aGame.add("Sue")
        
        repeat {
            aGame.roll(Int(arc4random_uniform(6)))
            if (arc4random_uniform(9) == 7) {
                notAWinner = aGame.wrongAnswer()
            } else {
                notAWinner = aGame.wasCorrectlyAnswered()
            }
        } while (notAWinner)
    }
}
