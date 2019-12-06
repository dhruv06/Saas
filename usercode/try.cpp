#include<bits/stdc++.h>
using namespace std;
class tim{
public:
int hour,minute,second;
static int s;
public:
void set(int,int m,int s);
void dis(tim &t1,tim &t2);
};

int main(){
tim t1,t2;
t1.set(1,2,3);
t2.set(4,5,6);
t1.dis(t1,t2);
t2.dis(t1,t2);
return 0;
}
int tim::s = 10;


void tim::set(int ho,int m,int st){
hour=ho;
minute=m;
second=st;
}
void tim::dis(tim &t1,tim &t2){
tim::hour=7;
tim::minute=8;
tim::second=9;
cout<< t1.hour << ' ' << t1.minute << ' ' << t1.second  << ' ' << t1.s << endl;
cout<< t2.hour << ' ' << t2.minute << ' ' << t2.second  << ' ' << t2.s << endl;
cout<< hour << ' ' << minute << ' ' << second  << ' ' << s << endl;
cout<< tim::hour << ' ' << tim::minute << ' ' << tim::second  << ' ' <<  tim::s << endl;
}
Hi there!