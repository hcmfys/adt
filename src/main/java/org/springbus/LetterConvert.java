package org.springbus;

// 6. Z 字形变换
// 难度
// 中等
//
// 619
//
// 收藏
//
// 分享
// 切换为英文
// 关注
// 反馈
// 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
//
// 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
//
// L   C   I   R
// E T O E S I I G
// E   D   H   N
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
//
// 请你实现这个将字符串进行指定行数变换的函数：
//
// string convert(string s, int numRows);
// 示例 1:
//
// 输入: s = "LEETCODEISHIRING", numRows = 3
// 输出: "LCIRETOESIIGEDHN"
// 示例 2:
//
// 输入: s = "LEETCODEISHIRING", numRows = 4
// 输出: "LDREOEIIECIHNTSG"
// 解释:
//
// L     D     R
// E   O E   I I
// E C   I H   N
// T     S     G
public class LetterConvert {

    public static void main(String[] args) {
        String t = "LEETCODEISHIRING";
        t = "PAYPALISHIRING";
        t = "A";
        t = "ABC";
        t = "hjouvsuyoypayulyeimuotehzriicfskpggkbbipzzrzucxamludfykgruowzgiooobppleqlwphapjnadqhdcnvwdtxjbmyppphauxnspusgdhiixqmbfjxjcvudjsuyibyebmwsiqyoygyxymzevypzvjegebeocfuftsxdixtigsieehkchzdflilrjqfnxztqrsvbspkyhsenbppkqtpddbuotbbqcwivrfxjujjddntgeiqvdgaijvwcyaubwewpjvygehljxepbpiwuqzdzubdubzvafspqpqwuzifwovyddwyvvburczmgyjgfdxvtnunneslsplwuiupfxlzbknhkwppanltcfirjcddsozoyvegurfwcsfmoxeqmrjowrghwlkobmeahkgccnaehhsveymqpxhlrnunyfdzrhbasjeuygafoubutpnimuwfjqsjxvkqdorxxvrwctdsneogvbpkxlpgdirbfcriqifpgynkrrefx";


        int row = 2;
        row = 503;
        String ret = new LetterConvert().convert(t, row);
        System.out.println(ret);
    }

    public String convert(String s, int numRows) {

        if (s == null || s.equals("")) {
            return "";
        }

        if (numRows == 1 || s.length() == 1) {
            return s;
        }
        int size = s.length();
        int col = size;
        String data[][] = new String[numRows][col];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < col; j++) {
                data[i][j] = "X";
            }
        }
        int q = 0;
        for (int i = 0; i < col; i++) {
            System.out.println(" i=" + i);

            for (int c = 0; c < numRows; c++) {
                if (q < s.length()) {
                    data[c][i] = s.substring(q, q + 1);
                    q++;
                }

            }

            for (int c = 1; c <= numRows - 2; c++) {
                if (q < s.length()) {
                    data[numRows - c - 1][i + c] = s.substring(q, q + 1);
                    q++;
                }

            }
            i += numRows - 2;
        }


        String rets = "";

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < col; j++) {
                if (data[i][j].equals("X")) {
                    data[i][j] = "";
                }
                System.out.print(data[i][j] + "\t");
                rets += data[i][j];
            }
            System.out.println("");
        }
        return rets;
    }
}
