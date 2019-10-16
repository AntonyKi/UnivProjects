package AlgStructCrypto.Lab1;

import java.util.ArrayList;
import java.util.List;

public class LongNum {
    private List<Integer> value = new ArrayList<>();
    private static int base = 10;
    public boolean fromString(String num){
        StringBuilder numm = new StringBuilder(num);
        numm.reverse();
        num = numm.toString();
        value = new ArrayList<>();
        for(char a : num.toCharArray()){
            if(a<48 || a>57)return false;
            value.add(a - 48);
        }
        return true;
    }
    public LongNum copy(){
        LongNum res = new LongNum();
        for(int n : this.value){
            res.add(n);
        }
        return res;
    }
    public String getString(){
        String res = "";
        for(int i = value.size()-1;i>=0;--i){
            res+= value.get(i);
        }
        return res;
    }
    public Integer get(int index) {
        try {
            return value.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void normalize() {
        int i = this.size() - 1;
        while (this.get(i) == 0) {
            if(this.size()==1)break;
            this.value.remove(i);
            i--;
        }
    }

    public void set(int index, int val) {
        value.set(index, val);
    }

    public void add(int val) {
        value.add(val);
    }

    public int size() {
        return this.value.size();
    }

    public int compareWith(LongNum b) {
        if (this.value.size() == b.size()) {
            for (int i = b.size() - 1; i >= 0; i--) {
                if (this.value.get(i) < b.get(i)) return -1;
                if (this.value.get(i) > b.get(i)) return 1;
            }
            return 0;
        } else if (this.value.size() < b.size()) return -1;
        else return 1;
    }

    public LongNum addition(LongNum b) {
        int newSize = Math.max(this.size(), b.size());
        int carry = 0;
        LongNum res = new LongNum();
        for(int i =0;i<this.size();i++){
            res.add(this.get(i));
        }
        for (int i = 0; i <= newSize; i++) {
            if (b.get(i) == null) b.add(0);
            if(this.get(i) == null)this.add(0);
            if (res.get(i) == null) res.add(0);
            res.set(i, carry + this.get(i) + b.get(i));
            carry = Math.floorDiv(res.get(i), base);
            res.set(i, Math.floorMod(res.get(i), base));
        }
        this.normalize();
        res.normalize();
        b.normalize();
        return res;
    }

    public LongNum subst(LongNum b) {
        int newSize = Math.max(this.size(), b.size());
        int n = this.size()- b.size();
        for(int i = 0;i<n;i++){
            b.add(0);
        }
        LongNum res = this.copy();
        /*if (this.compareWith(b) == 0) {
            res.value = new ArrayList<Integer>();
            res.add(0);
            return res;
        } else if (this.compareWith(b) == -1) {
            for (int i = newSize - 1; i >= 0; i--) {
                int newN = res.get(i) - b.get(i);
                if (newN > 0) {
                    res.set(i + 1, res.get(i + 1) + 1);
                    newN = -base;
                }
                res.set(i, newN);
            }
        } else {*/
            for (int i = newSize - 1; i >= 0; i--) {
                int newN = res.get(i) - b.get(i);
                if (newN < 0) {
                    res.set(i + 1, res.get(i + 1) - 1);
                    newN = +base;
                }
                res.set(i, newN);
            }
 //       }
        b.normalize();
        res.normalize();
        return res;
    }

    public LongNum multiply(LongNum b) {
        LongNum res = new LongNum();
        for (int i = 0; i < this.size(); i++) {
            int add = 0;
            int j;
            for ( j = 0; j < b.size(); ++j) {
                if(res.get(i + j)==null)res.add(0);
                add += res.get(i + j) + this.get(i) * b.get(j);
                res.set(i + j, add % base);
                add /= base;
            }
            while(add>0){
                if(res.get(i + j)==null)res.add(0);
                res.set(i + j, add % base);
                add /= base;
                j++;
            }
        }
        res.normalize();
        return res;
    }

//for binary search 2 division
    public LongNum div2(LongNum divided){
        LongNum res = divided.copy();
        for(int i = res.size()-1;i>=0;i--){
            if(i!=0)res.set(i-1,res.get(i-1)+(res.get(i)%2)*base);
            res.set(i , Math.floorDiv(res.get(i),2));
        }
        return res;
    }

    public LongNum sqrt(){
        LongNum r = new LongNum();
        LongNum l = new LongNum();
        int newSMax = Math.floorDiv(this.size(), 2) + Math.floorMod(this.size(), 2);
        for(int i =0;i <newSMax;i++){
            r.add(9);
        }
        for(int i = 0;i<newSMax-1;i++){
            l.add(0);
        }
        l.add(1);
        LongNum m;
        LongNum one = new LongNum();
        one.add(1);
        while(one.compareWith(r.subst(l))!=0){
            m = div2(l.addition(r));
            LongNum mm = m.multiply(m);
            if(mm.compareWith(this) == 0)return m;
            else if(mm.compareWith(this)==-1)l = m.copy();
            else r = m.copy();
        }
        return  l;
    }

    public LongNum divide(LongNum b){

        ///binary search setup

        LongNum l = new LongNum();
        LongNum r = new LongNum();
        for(int i = 0;i<(this.size() - b.size() + 1);i++) {
            r.add(9);
        }
        for(int i = 0; i <(this.size() - b.size() - 2);i++){
            l.add(0);
        }
        l.add(1);
        LongNum m;
        LongNum one = new LongNum();
        one.add(1);
        while(one.compareWith(r.subst(l))!=0){
            m = div2(l.addition(r));
            LongNum mm = m.multiply(b);
            if(mm.compareWith(this) == 0)return m;
            else if(mm.compareWith(this)==-1)l = m.copy();
            else r = m.copy();
        }
        return l;
    }

    public LongNum module(LongNum mod){
        LongNum div = this.divide(mod);
        return this.subst(div);
    }
}
