package zhongqiu.common.test;

import java.util.*;

/**
 * Created by wangzhongqiu on 2017/8/12.
 */
public class test_two {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<Entry, Entry> records = new LinkedHashMap<Entry, Entry>();
        while (scan.hasNext()) {
            String name = scan.next();
            int lines = scan.nextInt();
            int index = name.lastIndexOf('\\');
            //截取文件名
            if (index != -1)
                name = name.substring(index + 1);
            //生成Entry
            Entry entry = new Entry(name, lines);
            if (records.containsKey(entry)) {
                //合并
                Entry old = records.get(entry);
                old.count++;
            } else {
                //新的
                records.put(entry, entry);
            }
        }
        scan.close(); //获得结果
        Entry[] result = new Entry[records.size()];
        result = records.keySet().toArray(result);
        Arrays.sort(result);
        //输出结果
        int size = Math.min(8, result.length);
        for (int i = 0; i < size; i++) {
            System.out.println(result[i]);
        }
    }

    private static class Entry implements Comparable<Entry> {
        public String name;
        public int lines;
        public int count;

        public Entry(String n, int l) {
            this.name = n;
            this.lines = l;
            this.count = 1;
        }

        @Override
        public int compareTo(Entry e) {
            if (this.count < e.count)
                return 1;
            else if (this.count == e.count)
                return 0;
            else
                return -1;
        }

        @Override
        public int hashCode() {
            return name.hashCode() + lines;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;
            if (obj instanceof Entry) {
                Entry e = (Entry) obj;
                if (e.name.equals(this.name) && e.lines == this.lines)
                    return true;
            }
            return false;
        }

        @Override
        public String toString() {
            if (name.length() <= 16)
                return name + " " + lines + " " + count;
            else
                return name.substring(name.length() - 16, name.length()) + " " + lines + " " + count;
        }
    }
}
