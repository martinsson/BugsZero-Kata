//
//  Game.swift
//  bugzero-kata
//
//  Created by Frédéric Ruaudel on 20/04/2018.
//

import Foundation

class Game {
    var players = [String]()
    var places = [Int]()
    var purses = [Int]()
    var inPenaltyBox = [Bool]()
    
    var popQuestions = [String]()
    var scienceQuestions = [String]()
    var sportsQuestions = [String]()
    var rockQuestions = [String]()
    
    var currentPlayer: Int = 0
    var isGettingOutOfPenaltyBox: Bool = false
    
    init() {
        for i in 0..<50 {
            popQuestions.append("Pop question \(i)")
            scienceQuestions.append("Science question \(i)")
            sportsQuestions.append("Sports question \(i)")
            rockQuestions.append(createRockQuestions(index: i))
        }
    }
    
    func createRockQuestions(index: Int) -> String {
        return "Rock question \(index)"
    }
    
    func isPlayable() -> Bool {
        return (howManyPlayers() >= 2)
    }
    
    func add(_ playerName: String) -> Bool {
        players.append(playerName)
        places.append(0)
        purses.append(0)
        inPenaltyBox.append(false)
        
        print("\(playerName) was added")
        print("They are player number \(players.count)")
        
        return true
    }
    
    func howManyPlayers() -> Int {
        return players.count
    }
    
    func roll(_ roll: Int) {
        print("\(players[currentPlayer]) is the current player")
        print("They have rolled a \(roll)")
        
        if inPenaltyBox[currentPlayer] {
            if roll % 2 != 0 {
                isGettingOutOfPenaltyBox = true
                print("\(players[currentPlayer]) is getting out of the penalty box")
                movePlayerAndAskQuestion(roll)
            } else {
                print("\(players[currentPlayer]) is not getting out of the penalty box")
                isGettingOutOfPenaltyBox = false
            }
        } else {
            movePlayerAndAskQuestion(roll)
        }
    }
    
    private func movePlayerAndAskQuestion(_ roll: Int) {
        places[currentPlayer] = places[currentPlayer] + roll
        if places[currentPlayer] > 11 { places[currentPlayer] = places[currentPlayer] - 12 }
        
        print("\(players[currentPlayer])'s new location is \(places[currentPlayer])")
        print("The category is \(currentCategory())")
        
        askQuestion()
    }
    
    private func askQuestion() {
        if (currentCategory() == "Pop") {
            print(popQuestions.remove(at: 0))
        }
        if (currentCategory() == "Science") {
            print(scienceQuestions.remove(at: 0))
        }
        if (currentCategory() == "Sports") {
            print(sportsQuestions.remove(at: 0))
        }
        if (currentCategory() == "Rock") {
            print(rockQuestions.remove(at: 0))
        }
    }
    
    private func currentCategory() -> String {
        if places[currentPlayer] == 0 { return "Pop" };
        if places[currentPlayer] == 4 { return "Pop" };
        if places[currentPlayer] == 8 { return "Pop" };
        if places[currentPlayer] == 1 { return "Science" };
        if places[currentPlayer] == 5 { return "Science" };
        if places[currentPlayer] == 9 { return "Science" };
        if places[currentPlayer] == 2 { return "Sports" };
        if places[currentPlayer] == 6 { return "Sports" };
        if places[currentPlayer] == 10 { return "Sports" };
        return "Rock";
    }
    
    func wasCorrectlyAnswered() -> Bool {
        if inPenaltyBox[currentPlayer] {
            if isGettingOutOfPenaltyBox {
                print("Answer was correct!!!!")
                currentPlayer += 1
                if (currentPlayer == players.count) { currentPlayer = 0 }
                purses[currentPlayer] += 1
                print("\(players[currentPlayer]) now has \(purses[currentPlayer]) Gold Coins.")
                let winner = didPlayerWin()
                
                return winner
            } else {
                currentPlayer += 1
                if (currentPlayer == players.count) { currentPlayer = 0 }
                return true
            }
        } else {
            print("Answer was correct!!!!")
            purses[currentPlayer] += 1
            print("\(players[currentPlayer]) now has \(purses[currentPlayer]) Gold Coins.")
            let winner = didPlayerWin()
            currentPlayer += 1
            if (currentPlayer == players.count) { currentPlayer = 0 }
            
            return winner
        }
    }
    
    func wrongAnswer() -> Bool {
        print("Question was incorrectly answered")
        print("\(players[currentPlayer]) was sent to the penalty box")
        inPenaltyBox[currentPlayer] = true
        
        currentPlayer += 1
        if (currentPlayer == players.count) { currentPlayer = 0 }
        return true
    }
    
    private func didPlayerWin() -> Bool {
        return !(purses[currentPlayer] == 6)
    }
}
