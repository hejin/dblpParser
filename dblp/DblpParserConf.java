//
// Copyright (c)2015, dblp Team (University of Trier and
// Schloss Dagstuhl - Leibniz-Zentrum fuer Informatik GmbH)
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// (1) Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
//
// (2) Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// (3) Neither the name of the dblp team nor the names of its contributors
// may be used to endorse or promote products derived from this software
// without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
// ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// DISCLAIMED. IN NO EVENT SHALL DBLP TEAM BE LIABLE FOR ANY
// DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
// (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
// LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
// ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
// SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.dblp.mmdb.Field;
import org.dblp.mmdb.Person;
import org.dblp.mmdb.PersonName;
import org.dblp.mmdb.Publication;
import org.dblp.mmdb.RecordDb;
import org.dblp.mmdb.RecordDbInterface;
import org.dblp.mmdb.TableOfContents;
import org.xml.sax.SAXException;


@SuppressWarnings("javadoc")
class DblpParserConf {

    public static void main(String[] args) {

        // we need to raise entityExpansionLimit because the dblp.xml has millions of entities
        //
        boolean load_db = true;
        System.setProperty("entityExpansionLimit", "10000000");
        if (args.length != 2) {
            System.err.format("Usage: java %s <dblp-xml-file> <dblp-dtd-file>\n", DblpParserConf.class.getName());
            System.exit(0);
        }
        String dblpXmlFilename = args[0];
        String dblpDtdFilename = args[1];

        System.out.println("building the dblp main memory DB ...");
        long startTime = System.nanoTime();
        RecordDbInterface dblp;
        //if (load_db == true) {
        {
            try {
                dblp = new RecordDb(dblpXmlFilename, dblpDtdFilename, false);
            }
            catch (final IOException ex) {
                System.err.println("cannot read dblp XML: " + ex.getMessage());
                return;
            }
            catch (final SAXException ex) {
                System.err.println("cannot parse XML: " + ex.getMessage());
                return;
            }
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            long elapsedTimeInSeconds = elapsedTime / 1000000000;

            System.out.format("MMDB ready after %d seconds: %d publs, %d pers\n\n", elapsedTimeInSeconds, dblp.numberOfPublications(), dblp.numberOfPersons());
        }
        //

        // initialize the conf and volume info
        Map<String, LinkedList<Integer>> conf_metadata  = new HashMap<>();
        // PPoPP
        LinkedList<Integer> list_ppopp = new LinkedList<>();
        list_ppopp.add(2020);
        list_ppopp.add(2025);
        conf_metadata.put("ppopp", list_ppopp);
        // DAC
        LinkedList<Integer> list_dac = new LinkedList<>();
        list_dac.add(2020);
        list_dac.add(2024);
        conf_metadata.put("dac", list_dac);
        // HPCA
        LinkedList<Integer> list_hpca = new LinkedList<>();
        list_hpca.add(2020);
        list_hpca.add(2024);
        conf_metadata.put("hpca", list_hpca);
        // MICRO
        LinkedList<Integer> list_micro = new LinkedList<>();
        list_micro.add(2020);
        list_micro.add(2024);
        conf_metadata.put("micro", list_micro);
        // SC
        LinkedList<Integer> list_sc = new LinkedList<>();
        list_sc.add(2020);
        list_sc.add(2024);
        conf_metadata.put("sc", list_sc);
        // USENIX Security
        LinkedList<Integer> list_soups = new LinkedList<>();
        list_soups.add(2020);
        list_soups.add(2024);
        conf_metadata.put("soups", list_soups);

        // ISCA
        LinkedList<Integer> list_isca = new LinkedList<>();
        list_isca.add(2020);
        list_isca.add(2024);
        conf_metadata.put("isca", list_isca);
        // USENIX ATC
        LinkedList<Integer> list_usenix = new LinkedList<>();
        list_usenix.add(2020);
        list_usenix.add(2024);
        conf_metadata.put("usenix", list_usenix);
        // EuroSys
        LinkedList<Integer> list_eurosys = new LinkedList<>();
        list_eurosys.add(2020);
        list_eurosys.add(2024);
        conf_metadata.put("eurosys", list_eurosys);

        // CCS
        LinkedList<Integer> list_ccs = new LinkedList<>();
        list_ccs.add(2020);
        list_ccs.add(2024);
        conf_metadata.put("ccs", list_ccs);
        // S&P
        LinkedList<Integer> list_sp = new LinkedList<>();
        list_sp.add(2020);
        list_sp.add(2024);
        conf_metadata.put("sp", list_sp);
        // NDSS
        LinkedList<Integer> list_ndss = new LinkedList<>();
        list_ndss.add(2020);
        list_ndss.add(2025);
        conf_metadata.put("ndss", list_ndss);



        // dump the conf metadata map
        LinkedList<String> confTocs = new LinkedList<>();
        Iterator<Map.Entry<String, LinkedList<Integer>>> iterator = conf_metadata.entrySet().iterator();
        while (iterator.hasNext()) { 
            Map.Entry<String, LinkedList<Integer>> entry = iterator.next();
            String conf_name = entry.getKey();
            System.out.println(conf_name + ":");
            int from, to;
            from = entry.getValue().getFirst();
            to = entry.getValue().getLast();
            //System.out.println("\t" + from + " -> " + to);
            assert(to > from);
            // generate the toc for 
            String prefix = "db/conf/" + conf_name + "/" + conf_name;
            for (int i = from; i <= to; i ++) {
                String toc_string = prefix + Integer.toString(i) + ".bht";
                //System.out.println("\t" + toc_string);
                confTocs.add(toc_string);
            }
        }

        System.out.println("------------------------------------------------");
        for (String toc : confTocs) {
            System.out.print("Processing " + toc);
        //String toc = confTocs.getFirst();
            //System.out.print(toc + " : ");
            int lastSlashIndex = toc.lastIndexOf('/');
            int lastDotIndex = toc.lastIndexOf('.');
            String baseName = toc.substring(lastSlashIndex + 1, lastDotIndex);
            //System.out.println(baseName);
            // write to file in output directory
            String filePathTitle = "./conf/" + baseName + ".txt";
            String filePathDoi   = "./conf/" + baseName + ".doi.txt";
            //String contentToAppend = filePath;
            //TableOfContents focs2010Toc = dblp.getToc("db/conf/focs/focs2020.bht");
            TableOfContents content = dblp.getToc(toc);
            System.out.println("finding URLs of " + toc + " publications ...");
            String paper_titles = "";
            String paper_doies  = "";
            for (Publication publ : content.getPublications()) {
                for (Field fld : publ.getFields("title")) {
                    paper_titles += fld.value() + "\n";
                }
                for (Field fld : publ.getFields("ee")) {
                    paper_doies  += fld.value() + "\n";
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathTitle, true))) {
                writer.write(paper_titles);
            } catch (IOException e) {
                System.err.println("IO error: " + e.getMessage());
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathDoi, true))) {
                writer.write(paper_doies);
            } catch (IOException e) {
                System.err.println("IO error: " + e.getMessage());
            }

           System.out.println(" ... done.");
        } // for

        //TableOfContents focs2010Toc = dblp.getToc("db/conf/focs/focs2020.bht");
        System.out.println("all done.");
    }

}
