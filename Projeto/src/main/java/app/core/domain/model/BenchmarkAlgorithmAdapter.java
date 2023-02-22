package app.core.domain.model;

import app.core.domain.shared.BenchmarkAlgorithm;
import com.isep.mdis.Sum;

public class BenchmarkAlgorithmAdapter implements BenchmarkAlgorithm {

    int[] seq;

    public BenchmarkAlgorithmAdapter(int[] seq) {
        this.seq = seq;
    }

    @Override
    public int[] sum() {
        return Sum.Max(seq);
    }
}
