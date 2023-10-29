package baseball.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;

import baseball.controller.BaseballService;
import baseball.dto.input.BaseballDto;
import baseball.dto.output.GameResultDto;
import baseball.model.Baseball;
import baseball.model.BaseballNumber;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;

class BaseballServiceTest {

    private static MockedStatic<Randoms> randoms;

    @BeforeAll
    static void beforeAll() {
        randoms = mockStatic(Randoms.class);
    }

    @AfterAll
    static void afterAll() {
        randoms.close();
    }

    private static Baseball createBaseball(Integer... input) {
        List<BaseballNumber> list = Arrays.stream(input)
                .map(BaseballNumber::new)
                .toList();
        return Baseball.from(list);
    }

    @Nested
    @DisplayName("1-9까지 임의의 서로 다른 세 숫자를 생성할 시")
    class createAnswerBaseball {

        @DisplayName("성공적으로 생성한다.")
        @Test
        void success() {
            //given
            given(Randoms.pickNumberInRange(anyInt(), anyInt())).willReturn(5, 1, 1, 2, 2);

            //when
            Baseball answerBaseball = BaseballService.createAnswerBaseball();

            //then
            assertThat(answerBaseball).isNotNull();
            Assertions.assertThat(answerBaseball.getBaseballNumbers())
                    .extracting(BaseballNumber::getNumber)
                    .containsExactly(5, 1, 2);
        }
    }

    private static BaseballDto createBaseballDto(String baseball) {
        return new BaseballDto(baseball);
    }

    @Nested
    @DisplayName("숫자 야구의 결과를 계산할 시")
    class calculateResult {

        static Stream<Arguments> getSuccessTestArgument() {
            return Stream.of(
                    Arguments.of(createBaseball(1, 2, 3), createBaseballDto("123"), 0, 3),
                    Arguments.of(createBaseball(1, 2, 4), createBaseballDto("123"), 0, 2),
                    Arguments.of(createBaseball(1, 3, 2), createBaseballDto("123"), 2, 1),
                    Arguments.of(createBaseball(4, 5, 6), createBaseballDto("123"), 0, 0)
            );
        }

        @DisplayName("성공적으로 결과를 반환한다.")
        @MethodSource("getSuccessTestArgument")
        @ParameterizedTest(name = "정답: {0} 추측: {1} ball: {2}, strike: {3}")
        void success(Baseball answer, BaseballDto guess, int ballCount, int strikeCount) {
            //given
            //when
            GameResultDto gameResultDto = BaseballService.calculateResult(answer, guess);

            //then
            assertThat(gameResultDto).isNotNull();
            assertThat(gameResultDto.ballCount()).isEqualTo(ballCount);
            assertThat(gameResultDto.strikeCount()).isEqualTo(strikeCount);
        }
    }
}