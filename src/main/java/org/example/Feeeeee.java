package org.example;

import com.alibaba.fastjson2.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@NoArgsConstructor
@Data
public class Feeeeee {


    private String message;
    private ResultDTO result;
    private Integer code;

    public static void main(String[] args) throws IOException, InterruptedException {


        //System.out.println(new Feeeeee().isRectangleCover(new int[][]{{0, 0, 1, 1}, {0, 0, 2, 1}, {1, 0, 2, 1}, {0, 2, 2, 3}}));
        System.out.println(new Feeeeee().isRectangleCover(new int[][]{{1,1,3,3}, {3,1,4,2}, {3,2,4,4}, {1,3,2,4},{2,3,3,4}}));

        //curl 'https://nss.netease.com/api/cmdb/nagent/cmd?ids=14373874' \
        //  -H 'accept: application/json' \
        //  -H 'accept-language: zh-CN,zh;q=0.9,en;q=0.8' \
        //  -H 'content-type: application/json' \
        //  -H 'cookie: hb_MA-8D32-CBB074308F88_source=docs.popo.netease.com; hb_MA-9990-D3E4A3F66CF0_source=login.netease.com; hb_MA-8442-38F0144BBDB2_source=login.netease.com; hb_MA-8391-8FFD554DBEE5_source=nks.netease.com; hb_MA-84C2-1746F3C61AD5_source=login.netease.com; hb_MA-8993-0A09EA47275D_source=hz.oa.netease.com; cookies_user_key2="eyJhcHBBZG1pbiI6ZmFsc2UsInN5c3RlbUFkbWluIjpmYWxzZSwidXNlcklkIjoiaHVhY2hhbzAxQGNvcnAubmV0ZWFzZS5jb20iLCJ1c2VyTmFtZSI6IuWNjui2hSIsInRva2VuIjoiYmQxOWFlODItZDAxNC00ZjBkLThlMWItNTI0OTA2NjQwYmFhIn0="; ntes-km4-canary=false; hb_MA-809E-C9B7A6CD76FA_source=login.netease.com; hb_MA-91DF-2127272A00D5_source=betayun.netease.com; _ga_XVRD1WG0L5=GS1.2.1728461952.3.1.1728462375.0.0.0; _ga=GA1.2.849223188.1726815451; _ga_S4G8W6TW6D=GS1.1.1728526637.5.1.1728526664.33.0.0; mp_MA-91DF-2127272A00D5_hubble=%7B%22deviceUdid%22%3A%20%22c5cc9563-4a5e-45c0-a2a7-c967f25c4c18%22%2C%22updatedTime%22%3A%201728526724898%2C%22sessionStartTime%22%3A%201728526664803%2C%22sessionReferrer%22%3A%20%22https%3A%2F%2Fyun.gzbetayun.netease.com%2Fdashboard%23%2Fnat%2Fcreate%22%2C%22sessionUuid%22%3A%20%2239be870a-b6b5-459c-a691-799b11187c31%22%2C%22initial_referrer%22%3A%20%22%24direct%22%2C%22initial_referring_domain%22%3A%20%22%24direct%22%2C%22persistedTime%22%3A%201728526664803%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22fc_performance_monitor_develop%22%2C%22time%22%3A%201728526724898%7D%7D; NSS=f849c9d57bdee2033fa0c4bc33682fd7231ebe72; op_state_id_1.0=plro71ej1s; first_load=1; NTESwebSI=7E7107AE0F2AEBC179A6EBC1701A8201.sentry-web-docker-ops-online-3-7jl0g-brwv3-57fb7b8df4-k67cz-8081; op_session_id_1.0=4E48EC9C78CDBA463776038BB84233DE1844DF6F57CB28FDF2C8B74B70BF64F293A69FEC3F591ACC952E0844E6D5DA373C2ED735C9DC539172CE070A22C61CF3910C169AE8F7DADE7F542F631CE9FE9EC29F288830954D7A6EDD9145CBA1200829E63AFB4AFF16C598C831994BCBEB8832161E969F30AFD183593608E8AF4A91084BF8730AD804210A84A4300B0ECFC72C8DC728B03EAD39E4A54C040D07AC2B626FB50729C2A3F0575056FE32593EFA' \
        //  -H 'origin: https://nss.netease.com' \
        //  -H 'priority: u=1, i' \
        //  -H 'referer: https://nss.netease.com/cmdb/server/maintenance?searchword=10' \
        //  -H 'sec-ch-ua: "Chromium";v="128", "Not;A=Brand";v="24", "Google Chrome";v="128"' \
        //  -H 'sec-ch-ua-mobile: ?0' \
        //  -H 'sec-ch-ua-platform: "Linux"' \
        //  -H 'sec-fetch-dest: empty' \
        //  -H 'sec-fetch-mode: cors' \
        //  -H 'sec-fetch-site: same-origin' \
        //  -H 'user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36' \
        //  --data-raw '{"taskTemplate":"","user":"nobody","timeout":"10","outputType":"2","ids":[14373874],"cmd":"ls","type":"同步"}'
//        for (String readAllLine : Files.readAllLines(Path.of("youdao.result"))) {
//            String[] s = readAllLine.split(" ");
//            extracted(s[0], s[1]);
//        }

    }

    private static void extracted(String sn, String hostname) {
        for (int i = 0; i < 3; i++) {
            try {
                HttpClient client = HttpClient.newBuilder().build();
                String body = client.send(HttpRequest.newBuilder()
                        .uri(URI.create("https://nss.netease.com/api/cmdb/nagent/cmd?ids=" + sn))
                        .POST(HttpRequest.BodyPublishers.ofString(JSON.toJSONString(new HashMap<>() {{
                            put("taskTemplate", "");
                            put("user", "root");
                            put("timeout", "20");
                            put("outputType", "2");
                            put("cmd", "ps -ef | grep -v \"\\[\"");
                            put("type", "同步");
                        }})))
                        .header("cookie", " hb_MA-8D32-CBB074308F88_source=docs.popo.netease.com; hb_MA-9990-D3E4A3F66CF0_source=login.netease.com; hb_MA-8442-38F0144BBDB2_source=login.netease.com; hb_MA-8391-8FFD554DBEE5_source=nks.netease.com; hb_MA-84C2-1746F3C61AD5_source=login.netease.com; hb_MA-8993-0A09EA47275D_source=hz.oa.netease.com; cookies_user_key2=\"eyJhcHBBZG1pbiI6ZmFsc2UsInN5c3RlbUFkbWluIjpmYWxzZSwidXNlcklkIjoiaHVhY2hhbzAxQGNvcnAubmV0ZWFzZS5jb20iLCJ1c2VyTmFtZSI6IuWNjui2hSIsInRva2VuIjoiYmQxOWFlODItZDAxNC00ZjBkLThlMWItNTI0OTA2NjQwYmFhIn0=\"; ntes-km4-canary=false; hb_MA-809E-C9B7A6CD76FA_source=login.netease.com; hb_MA-91DF-2127272A00D5_source=betayun.netease.com; _ga_XVRD1WG0L5=GS1.2.1728461952.3.1.1728462375.0.0.0; _ga=GA1.2.849223188.1726815451; _ga_S4G8W6TW6D=GS1.1.1728526637.5.1.1728526664.33.0.0; mp_MA-91DF-2127272A00D5_hubble=%7B%22deviceUdid%22%3A%20%22c5cc9563-4a5e-45c0-a2a7-c967f25c4c18%22%2C%22updatedTime%22%3A%201728526724898%2C%22sessionStartTime%22%3A%201728526664803%2C%22sessionReferrer%22%3A%20%22https%3A%2F%2Fyun.gzbetayun.netease.com%2Fdashboard%23%2Fnat%2Fcreate%22%2C%22sessionUuid%22%3A%20%2239be870a-b6b5-459c-a691-799b11187c31%22%2C%22initial_referrer%22%3A%20%22%24direct%22%2C%22initial_referring_domain%22%3A%20%22%24direct%22%2C%22persistedTime%22%3A%201728526664803%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22fc_performance_monitor_develop%22%2C%22time%22%3A%201728526724898%7D%7D; NSS=f849c9d57bdee2033fa0c4bc33682fd7231ebe72; op_state_id_1.0=plro71ej1s; first_load=1; NTESwebSI=7E7107AE0F2AEBC179A6EBC1701A8201.sentry-web-docker-ops-online-3-7jl0g-brwv3-57fb7b8df4-k67cz-8081; op_session_id_1.0=4E48EC9C78CDBA463776038BB84233DE1844DF6F57CB28FDF2C8B74B70BF64F293A69FEC3F591ACC952E0844E6D5DA373C2ED735C9DC539172CE070A22C61CF3910C169AE8F7DADE7F542F631CE9FE9EC29F288830954D7A6EDD9145CBA1200829E63AFB4AFF16C598C831994BCBEB8832161E969F30AFD183593608E8AF4A91084BF8730AD804210A84A4300B0ECFC72C8DC728B03EAD39E4A54C040D07AC2B626FB50729C2A3F0575056FE32593EFA")
                        .header("Content-Type", "application/json")
                        .build(), HttpResponse.BodyHandlers.ofString()).body();
                Feeeeee feeeeee = JSON.parseObject(body, Feeeeee.class);
                Files.writeString(Path.of("result"), sn + "   " + hostname + "   " + feeeeee.result.outputs.get(0).output + "\n", StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
                System.out.println(feeeeee.result.outputs.get(0).output);
                break;
            } catch (Exception e) {
                try {
                    Files.writeString(Path.of("error"), sn + "  " + hostname + " " + e.getMessage() + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.WRITE);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }


    @NoArgsConstructor
    @Data
    public static class ResultDTO {
        private List<OutputsDTO> outputs;

        @NoArgsConstructor
        @Data
        public static class OutputsDTO {
            private Integer id;
            private String hostname;
            private String ip;
            private Integer exitCode;
            private Integer timeUsed;
            private String output;
            private Long time;
        }
    }

    public boolean isRectangleCover(int[][] rectangles) {
        int l = Integer.MAX_VALUE;
        int r = Integer.MIN_VALUE;
        int b = Integer.MAX_VALUE;
        int t = Integer.MIN_VALUE;
        Set<String> hashSet = new HashSet<>();
        long aren = 0;
        for (int i = 0; i < rectangles.length; i++) {
            int[] rectangle = rectangles[i];
            int[] lb = new int[]{rectangle[0], rectangle[1]};
            int[] lt = new int[]{rectangle[2], rectangle[1]};
            int[] rb = new int[]{rectangle[0], rectangle[3]};
            int[] rt = new int[]{rectangle[2], rectangle[3]};
            aren = aren + 1l * (rb[1] - lb[1]) * (lt[0] - lb[0]);
            String lbS = lb[0] + "#" + lb[1];
            String ltS = lt[0] + "#" + lt[1];
            String rbS = rb[0] + "#" + rb[1];
            String rtS = rt[0] + "#" + rt[1];
            if (!hashSet.contains(lbS)) {
                hashSet.add(lbS);
            } else {
                hashSet.remove(lbS);
            }
            if (!hashSet.contains(ltS)) {
                hashSet.add(ltS);
            } else {
                hashSet.remove(ltS);
            }
            if (!hashSet.contains(rbS)) {
                hashSet.add(rbS);
            } else {
                hashSet.remove(rbS);
            }
            if (!hashSet.contains(rtS)) {
                hashSet.add(rtS);
            } else {
                hashSet.remove(rtS);
            }

        }
        if (hashSet.size() == 4) {

            for (String s : hashSet) {

                String[] split = s.split("#");

                l = Math.min(l, Integer.valueOf(split[1]));
                r = Math.max(r, Integer.valueOf(split[1]));
                b = Math.min(b, Integer.valueOf(split[0]));
                t = Math.max(t, Integer.valueOf(split[0]));
            }

            if (aren == 1l * (r - l) * (t - b)) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

}
