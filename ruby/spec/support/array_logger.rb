class ArrayLogger
  attr_reader :messages

  def initialize
    @messages = []
  end

  def debug(message)
    @messages << message
  end

  alias info debug
  alias warn debug
  alias fatal debug
end
