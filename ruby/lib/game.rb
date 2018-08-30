class Game

  attr_accessor :players,
                :current_player,
                :in_penalty_box,
                :is_getting_out_of_penalty_box,
                :places,
                :purses,
                :pop_questions,
                :science_questions,
                :sports_questions,
                :rock_questions

  def initialize(logger)
    @logger = logger
    @players = []
    @places = []
    @purses = []
    @in_penalty_box = []
    @pop_questions = []
    @science_questions = []
    @sports_questions = []
    @rock_questions = []
    @current_player = 0

    0.upto(49) do |i|
      @pop_questions << "Pop Question #{i}"
      @science_questions << "Science Question #{i}"
      @sports_questions << "Sport Question #{i}"
      @rock_questions << create_rock_question(i)
    end
  end

  def add(player_name)
    players << player_name
    places[how_many_players - 1] = 0
    purses[how_many_players - 1] = 0
    in_penalty_box[how_many_players - 1] = false

    puts "#{player_name} was added"
    puts "They are player number #{players.size}"

    true
  end

  def how_many_players
    players.size
  end

  def did_player_win?
    !(purses[current_player] == 6)
  end

  def current_category
    return "Pop"     if places[current_player] == 0
    return "Pop"     if places[current_player] == 4
    return "Pop"     if places[current_player] == 8
    return "Science" if places[current_player] == 1
    return "Science" if places[current_player] == 5
    return "Science" if places[current_player] == 9
    return "Sports"  if places[current_player] == 2
    return "Sports"  if places[current_player] == 6
    return "Sports"  if places[current_player] == 10
    "Rock"
  end

  def create_rock_question(index)
    "Rock Question #{index}"
  end

  def is_playable?
    how_many_players >= 2
  end

  def ask_question
    if current_category == "Pop"
      puts pop_questions.shift
    end

    if current_category == "Science"
      puts science_questions.shift
    end

    if current_category == "Sports"
      puts sports_questions.shift
    end

    if current_category == "Rock"
      puts rock_questions.shift
    end
  end

  def roll(roll)
    puts "#{players[current_player]} is the current player"
    puts "They have rolled a #{roll}"

    if in_penalty_box[current_player]
      if roll % 2 != 0
        self.is_getting_out_of_penalty_box = true

        puts "#{players[current_player]} is getting out of the penalty box"
        move_player_and_ask_question(roll)
      else
        puts "#{players[current_player]} is not getting out of the penalty box"
        self.is_getting_out_of_penalty_box = false
      end
    else
      move_player_and_ask_question(roll)
    end
  end

  private def move_player_and_ask_question(roll)
    places[current_player] = places[current_player] + roll

    if places[current_player] > 11
      places[current_player] = places[current_player] - 12;
    end

    puts "#{players[current_player]}'s new location is #{places[current_player]}"
    puts "The category is #{current_category}"
    ask_question
  end

  private def do_answer_correctly
    puts "Answer was correct!!!!"
    self.current_player = current_player + 1
    self.current_player = 0 if current_player == players.size
    purses[current_player] = purses[current_player] + 1
    puts "#{players[current_player]} now has #{purses[current_player]} Gold Coins."
    winner = did_player_win?

    winner
  end

  def was_correctly_answered
    if in_penalty_box[current_player]
      if is_getting_out_of_penalty_box
        do_answer_correctly
      else
        self.current_player = current_player + 1
        self.current_player = 0 if current_player == players.size
        true
      end
    else
      do_answer_correctly
    end
  end

  def wrong_anwser
    puts "Question was incorrectly answered"
    puts "#{players[current_player]} was sent to the penalty box"
    in_penalty_box[current_player] = true

    self.current_player = current_player + 1
    self.current_player = 0 if current_player == players.size

    true
  end

  private def puts(message)
    @logger.info(message)
  end

end
