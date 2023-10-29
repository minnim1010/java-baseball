package baseball.common.random;

import camp.nextstep.edu.missionutils.Randoms;

public interface Random {

    static int getNumberInRange(int min, int max) {
        return Randoms.pickNumberInRange(min, max);
    }
}
