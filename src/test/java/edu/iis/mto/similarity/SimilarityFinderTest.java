package edu.iis.mto.similarity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import edu.iis.mto.searcher.SearchResult;
import org.junit.jupiter.api.Test;

class SimilarityFinderTest {
    public static final int[] emptySeq = {};
    public static final int[] fiveElementSeq = {1, 2, 3, 4, 7};
    public static final int[] anotherFiveElementSeqTwoSimilar = {1, 3, 5, 6, 8};

    /*
    ### Testy stanu ###
     */

    @Test
    void bothSeqAreEmptyExpectingOne() {
        SimilarityFinder simFinder = new SimilarityFinder(((elem, sequence) -> SearchResult.builder().build()));
        assertEquals(1.0, simFinder.calculateJackardSimilarity(emptySeq, emptySeq));
    }

    @Test
    void firstSeqIsEmptyExpectingZero() {
        SimilarityFinder simFinder = new SimilarityFinder(((elem, sequence) -> SearchResult.builder().withFound(false).build()));
        assertEquals(0.0, simFinder.calculateJackardSimilarity(emptySeq, fiveElementSeq));
    }

    @Test
    void secondSeqIsEmptyExpectingZero() {
        SimilarityFinder simFinder = new SimilarityFinder(((elem, sequence) -> SearchResult.builder().withFound(false).build()));
        assertEquals(0.0, simFinder.calculateJackardSimilarity(fiveElementSeq, emptySeq));
    }

    @Test
    void sameLenSeqExpectingTwo() {
        SimilarityFinder simFinder = new SimilarityFinder(((elem, sequence) -> {
            if (elem == 1 || elem == 3) return SearchResult.builder().withFound(true).build();
            return SearchResult.builder().withFound(false).build();
        }));
        assertEquals(0.25, simFinder.calculateJackardSimilarity(fiveElementSeq, anotherFiveElementSeqTwoSimilar));
    }

}
