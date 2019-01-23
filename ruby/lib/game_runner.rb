require_relative "game"

class GameRunner
  def initialize(random_generator, logger)
    @random_generator = random_generator
    @logger = logger
  end

  def run
    a_game = Game.new(@logger)

    a_game.add("Chet")
    a_game.add("Pat")
    a_game.add("Sue")

    begin
      a_game.roll(@random_generator.rand(5) + 1);

      if (@random_generator.rand(9) == 7)
        @not_a_winner = a_game.wrong_anwser
      else
        @not_a_winner = a_game.was_correctly_answered
      end
    end while @not_a_winner
  end
end
