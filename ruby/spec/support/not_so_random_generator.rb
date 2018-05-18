class NotSoRandomGenerator
  def initialize(expected_results)
    @cursor = -1
    @expected_results = expected_results
  end

  def rand(upper_limit)
    @cursor += 1

    if @cursor >= @expected_results.size
      raise "Not enough expected_results given"
    end

    (@expected_results[@cursor] * upper_limit).ceil
  end
end
