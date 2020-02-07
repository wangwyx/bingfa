package com.algo;

/**
 * @auth: wangyx-p
 * @date :2020/1/15 16:21
 * @Description:�ַ����Ƚ��㷨:���ַ�������hash,ȱ��:������ɳ�ͻ
 */
public class RK {
    public static int rabinKarp(String str, String pattern){

        //��������
        int m = str.length();
        //ģʽ���ĳ���
        int n = pattern.length();
        //����ģʽ����hashֵ
        int patternCode = hash(pattern);
        //�����������е�һ����ģʽ���ȳ����Ӵ�hashֵ
        int strCode = hash(str.substring(0, n));


        //��ģʽ����hashֵ�������ľֲ�hashֵ�Ƚϡ�
        //���ƥ�䣬����о�ȷ�Ƚϣ������ƥ�䣬���������������Ӵ���hashֵ��

        for (int i=0; i<m-n+1; i++) {
            if(strCode == patternCode && compareString(i, str, pattern)){
                return i;
            }
            //����������һ�֣�����������i��i+n��hashֵ
            if(i<m-n){
                strCode = nextHash(str, strCode, i, n);
            }
        }


        return -1;

    }


    private static int hash(String str){

        int hashcode = 0;
        //���������򵥵�hashcode���㷽ʽ��
        //��a����1����b����2����c����3.....Ȼ��λ���
        for (int i = 0; i < str.length(); i++) {
            hashcode += str.charAt(i)-'a';
        }

        return hashcode;

    }


    private static int nextHash(String str, int hash, int index, int n){
        hash -= str.charAt(index)-'a';
        hash += str.charAt(index+n)-'a';
        return hash;

    }


    private static boolean compareString(int i, String str, String pattern) {
        String strSub = str.substring(i, i+pattern.length());
        return strSub.equals(pattern);

    }


    public static void main(String[] args) {

        String str = "aacdesa1dsdfer";
        String pattern = "adsd";
        System.out.println("��һ�γ��ֵ�λ��:" + rabinKarp(str, pattern));
    }
}
