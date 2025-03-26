import edu.duke.StorageResource;
import util.GeneUtils;

public class Part1 {

    // Returns the index of the stopCodon that appears in frame from the startIndex
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        return GeneUtils.findStopCodon(dna, startIndex, stopCodon);
    }
    
    public void testFindStopCodon() {
        System.out.println("=== testFindStopCodon ===");

        String dna = "ATGxxxyyyTAAzzz";
        int result = findStopCodon(dna, 0, "TAA");
        System.out.println("DNA: " + dna);
        System.out.println("Stop codon: TAA, Result index: " + result + " (Expected: 9)");

        dna = "ATGxxxyyyTAAzzz";
        result = findStopCodon(dna, 0, "TAG");
        System.out.println("Stop codon: TAG, Result index: " + result + " (Expected: -1)");

        dna = "ATGxxxyyyTAAzzz";
        result = findStopCodon(dna, 0, "TGA");
        System.out.println("Stop codon: TGA, Result index: " + result + " (Expected: -1)");

        dna = "ATGxxTAAyyTAA";
        result = findStopCodon(dna, 0, "TAA");
        System.out.println("DNA: " + dna);
        System.out.println("Stop codon: TAA, Result index: " + result + " (Expected: -1)");

        dna = "ATGxxxxx";
        result = findStopCodon(dna, 0, "TAA");
        System.out.println("DNA: " + dna);
        System.out.println("Stop codon: TAA, Result index: " + result + " (Expected: -1)");

        System.out.println();
    }

    // Finds and returns a gene from the given DNA string.
    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        // Pick the smallest valid stop codon index
        int minIndex = dna.length();
        boolean foundStop = false;
        if (taaIndex != -1 && taaIndex < minIndex) {
            minIndex = taaIndex;
            foundStop = true;
        }
        if (tagIndex != -1 && tagIndex < minIndex) {
            minIndex = tagIndex;
            foundStop = true;
        }
        if (tgaIndex != -1 && tgaIndex < minIndex) {
            minIndex = tgaIndex;
            foundStop = true;
        }

        if (!foundStop) {
            return "";
        }

        return dna.substring(startIndex, minIndex + 3);
    }

    public void testFindGene() {
        System.out.println("=== testFindGene ===");

        String dna, result;

        dna = "GATTACA";
        result = findGene(dna);
        System.out.println("DNA: " + dna);
        System.out.println("Gene found: '" + result + "' (Expected: '')");

        dna = "AATGCTAACTAGCTGACTAAT";
        result = findGene(dna);
        System.out.println("DNA: " + dna);
        System.out.println("Gene found: '" + result + "' (Expected: ATGCGTAATTAA)");

        dna = "AATGCTAGGGTAATATGGT";
        result = findGene(dna);
        System.out.println("DNA: " + dna);
        System.out.println("Gene found: '" + result + "' (Expected: AATGCTAGGGTAA)");

        dna = "ATGTTTTTTTTTT";
        result = findGene(dna);
        System.out.println("DNA: " + dna);
        System.out.println("Gene found: '" + result + "' (Expected: '')");

        dna = "ATGCCCGGGAAATAACCC";
        result = findGene(dna);
        System.out.println("DNA: " + dna);
        System.out.println("Gene found: '" + result + "' (Expected: ATGCCCGGGAAATAA)");

        System.out.println();
    }

    // Original method that prints genes found in the given DNA string.
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            int atgIndex = dna.indexOf("ATG", startIndex);
            if (atgIndex == -1) break;

            int taaIndex = findStopCodon(dna, atgIndex, "TAA");
            int tagIndex = findStopCodon(dna, atgIndex, "TAG");
            int tgaIndex = findStopCodon(dna, atgIndex, "TGA");

            int minIndex = dna.length();
            int foundIndex = -1;

            if (taaIndex != -1 && taaIndex < minIndex) {
                minIndex = taaIndex;
                foundIndex = taaIndex;
            }
            if (tagIndex != -1 && tagIndex < minIndex) {
                minIndex = tagIndex;
                foundIndex = tagIndex;
            }
            if (tgaIndex != -1 && tgaIndex < minIndex) {
                minIndex = tgaIndex;
                foundIndex = tgaIndex;
            }

            if (foundIndex != -1) {
                System.out.println("Gene found: " + dna.substring(atgIndex, minIndex + 3));
                startIndex = minIndex + 3;
            } else {
                startIndex = atgIndex + 3; // Move past this "ATG"
            }
        }
    }

    public void testPrintAllGenes() {
        System.out.println("--- Testing printAllGenes ---");
        printAllGenes("ATGTAAGATGCCCTAGTGA"); // Expected: ATGTAA, ATGCCCTAG
        printAllGenes("ATGTTTTTTTTTGA");       // Expected: ATGTTTTTTTTTGA
        printAllGenes("ATGCGCTAATAGCGCTAG");    // Expected: ATGCGCTAA
        printAllGenes("ATGAAATGA");             // Expected: ATGAAATGA
        printAllGenes("TGAATGAAATAA");           // Expected: ATGAAATAA
        printAllGenes("ATGABCDEF");             // Expected:
        printAllGenes("TAAATGCCCTAG");           // Expected: ATGCCCTAG
        printAllGenes("ATGTAAXXXYYYATGZZZTAG");  // Expected: ATGTAA, ATGZZZTAG
        System.out.println();
    }
    
    // New method that collects all genes found in the given DNA string into a StorageResource.
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true) {
            int atgIndex = dna.indexOf("ATG", startIndex);
            if (atgIndex == -1) break;

            int taaIndex = findStopCodon(dna, atgIndex, "TAA");
            int tagIndex = findStopCodon(dna, atgIndex, "TAG");
            int tgaIndex = findStopCodon(dna, atgIndex, "TGA");

            int minIndex = dna.length();
            int foundIndex = -1;

            if (taaIndex != -1 && taaIndex < minIndex) {
                minIndex = taaIndex;
                foundIndex = taaIndex;
            }
            if (tagIndex != -1 && tagIndex < minIndex) {
                minIndex = tagIndex;
                foundIndex = tagIndex;
            }
            if (tgaIndex != -1 && tgaIndex < minIndex) {
                minIndex = tgaIndex;
                foundIndex = tgaIndex;
            }

            if (foundIndex != -1) {
                String gene = dna.substring(atgIndex, minIndex + 3);
                geneList.add(gene);
                startIndex = minIndex + 3;
            } else {
                startIndex = atgIndex + 3; // Move past this "ATG"
            }
        }
        return geneList;
    }
    
    // Test method for getAllGenes to ensure it collects the genes correctly.
    public void testGetAllGenes() {
        System.out.println("--- Testing getAllGenes ---");
        String dna = "ATGTAAGATGCCCTAGTGA";
        StorageResource genes = getAllGenes(dna);
        for (String gene : genes.data()) {
            System.out.println("Gene found: " + gene);
        }
        System.out.println();
    }
    
    // Main method to run all tests.
    public static void main(String[] args) {
        Part1 part1 = new Part1();
        part1.testFindStopCodon();
        part1.testFindGene();
        part1.testPrintAllGenes();
        part1.testGetAllGenes();
    }
}
