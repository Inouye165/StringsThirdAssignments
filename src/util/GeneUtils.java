package util;

public class GeneUtils {

    /**
     * Finds the index of the stop codon in the DNA string that is a multiple of 3 away from the startIndex.
     * 
     * @param dna The DNA string.
     * @param startIndex The index where the gene starts (index of "ATG").
     * @param stopCodon The stop codon to search for ("TAA", "TAG", or "TGA").
     * @return The index of the valid stop codon, or -1 if not found.
     */
    public static int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            // Check if the distance from startIndex is a multiple of 3
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
            currIndex = dna.indexOf(stopCodon, currIndex + 1);
        }
        return -1;
    }
}
