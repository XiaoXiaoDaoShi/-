#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <string.h>
#include <fstream>
using namespace std;
#define MAXQSIZE 100
#define OK 1
#define ERROR 0
#define random(x) (rand()%x)
#define MAX_SCLASS 2
#define MAX_BCLASS 3
typedef int Status;

char password[6];
int flag=0;

typedef struct
{
    char title[100];
    char author[100];
    int t[3];

} ElemType;

ElemType pM;

typedef struct music
{
    ElemType data;
    struct music *next;
}*MusicList, MusicNode;

typedef struct classify
{
    MusicList mList;
    int MusicType;
    int c;
    struct classify *classification;
    struct classify *next;
}*ClassifyList, ClassifyNode;

void adminOperation(ClassifyList &C);
Status printMusicList(MusicList M);

Status initMusicList(MusicList &M)
{
    M = new MusicNode;
    M->next = NULL;
    return OK;
}

int totalMusicList(MusicList M)
{
    int total=0;
    MusicNode *p=M;
    while(p)
    {
        p=p->next;
        total++;
    }
    return total-1;
}

Status getElem(MusicList M, int i, ElemType &e)   //get i-th elem
{
    MusicNode *p;
    p = M->next;
    int j=0;
    int n = totalMusicList(M);
    if(i>n || i<1)
        return ERROR;
    while(p&&(j<i-1))   //i-1th
    {
        p=p->next;
        ++j;
    }
    e=p->data;
    return OK;
}

Status musicListDelete(MusicList &M, int i)
{
    MusicNode *p=M,*q;
    int j=0;
    int n = totalMusicList(M);
    if(i>n || i<1)
        return ERROR;
    while((p->next) && (j<i-1))  //i-1th
    {
        p=p->next;
        ++j;
    }
    q = p->next;
    p->next=q->next;
    delete q;
    return OK;
}

Status musicListAdd(MusicList &M, ElemType e)    //add musiclist node
{
    MusicNode *p, *q;
    q = new MusicNode;
    p=M;
    if(M==NULL)
    {

        return ERROR;
    }

    while(p->next)
        p=p->next;

    q->data=e;
    q->next=NULL;
    p->next=q;          //add new node
    p=q;                //point to tail node
}

Status printMusicList(MusicList M)
{
    if(M==NULL)
        return ERROR;
    MusicNode *p;
    p=M->next;
    int index=1;
    while(p)
    {
        cout<<index<<". "<<p->data.title<<"   ---"<<p->data.author<<endl;
        p=p->next;
        index ++;
    }
    return OK;
}

void loadFile(MusicList &M)        //
{
    FILE *fp;
    fp=fopen("music.txt","r");

    char tmp[100],x[5],y[5],z[5];
    ElemType e;
    MusicNode *q,*p;
    p=M;
    int m;
    while(1)         //分段读取文件
    {
        m=fscanf(fp,"\n%[^#]#%[^#]#%[^#]#%[^#]#%[^\n]",&e.title,&e.author,&x,&y,&z);
        if(m==EOF)
            break;
        if(m!=5)
        {
            fgets(tmp,1024,fp);
            continue;
        }

        q=new MusicNode;

        e.t[0]=atoi(x);
        e.t[1]=atoi(y);
        e.t[2]=atoi(z);           //加到结构体ElemType中

        q->data=e;
        q->next=NULL;
        p->next=q;      //q插入尾结点
        p=q;
        //p指向新的尾结点
    }
    fclose(fp);
}

Status saveFile(MusicList &M, string filename)   //filename省略.txt 保存后的文件名
{
    string path_t;
    path_t = filename + "_temp.txt";  //temp file
    ofstream outfile(path_t,ios::out);
    if(!outfile)
        return ERROR;
    MusicNode *p;
    p=M->next;
    string content;
    while(p)
    {
        content=(string)(p->data.title)+"#"+(string)(p->data.author)+"#"+to_string(p->data.t[0])+"#"+to_string(p->data.t[1])+"#"+to_string(p->data.t[2]);
        outfile<<content<<endl;
        p=p->next;
    }
    outfile.close();
    filename =  filename + ".txt";
    remove(filename.data());                    //delete orginal file
    rename(path_t.data(), filename.data());        //rename file
    return OK;
}

Status loginAdmin()
{
    ifstream infile;
    infile.open("admin.txt");
    string content;
    getline(infile, content);
    infile.close();
    char *s=(char*)content.data();
    cout<<"请输入管理员密码"<<endl;
    for(int i=0; i<6; i++)
        cin>>password[i];
    for(int i=0; i<6; i++)
        if(password[i] != s[i])
            return ERROR;
    return OK;
}

Status initClassifyList(ClassifyList &C,int i)
{
    C=new ClassifyNode;
    C->next=NULL;
    C->classification=NULL;
    C->c=i;
    C->MusicType=0;
    return OK;
}

//Status addClassifyList(ClassifyList &C,  MusicList m, int i, int n)         //
//{
//    ClassifyNode *p, *q;
//    p=C;
//    while(p->next)
//    {
//        p=p->next;
//    }
//    q=new ClassifyNode;
//    q->c=i;
//    q->MusicType=n;
//    q->mList=m;
//    q->next = NULL;
//    p->next=q;
//    p=q;
//    return OK;
//}

Status addClassification(ClassifyList &C, ClassifyList cN)
{
    ClassifyNode *p;
    p=C;
    while(p->classification)
    {
        p=p->classification;
    }
    p->classification = cN;
    p=cN;
    return OK;
}

Status addMusicToClassification(ClassifyList &C, MusicList M, int i, int n)   //添加Music列到ClassifyList  i,n标记classicaton 对内容不起作用
{
    //n:1,2,3
    //i:1,2
    ClassifyList p,q;
    q=new ClassifyNode;
    p=C;
    while(p->next)
        p=p->next;
    q->mList = M;
    q->c = i;
    q->MusicType=n;
    q->next=NULL;
    q->classification=NULL;

    p->next=q;
    p=q;

    if(C->next==NULL)
        return ERROR;
    return OK;
}

Status disClassification(ClassifyList C,int i, int n, MusicList &m)    //对当前列表分类,返回新的列表m  i,n对起作用
{
    // i: 1,2
    if(C->c==i)         //已分过
        return OK;
    MusicList temp_m;
    temp_m = C->mList;
    temp_m=temp_m->next;
    int type=i-1;
    ElemType e;
    while(temp_m)
    {
        e = temp_m->data;

        if(e.t[type] == n)
        {
            musicListAdd(m,e);
        }
        temp_m=temp_m->next;
    }
    return OK;
}

Status addDisClassifyList(ClassifyList &C, int i)  //根据i大类添家classify分类
{
    //i:1,2,3
    ClassifyList new_C;
    initClassifyList(new_C,i);

    MusicList m;
    initMusicList(m);

    for(int n=1; n<=MAX_SCLASS; n++)           //根据大类，生成小类型new_C列
    {
        initMusicList(m);

        disClassification(C,i,n,m);

        addMusicToClassification(new_C,m,i,n);
    }

    addClassification(C,new_C);                 //C指想新new_C
}

Status disClassificationOperation(ClassifyList C,int n, ClassifyList &t)   //根据小类型分类操作，返回一个分好的类型
{
    ClassifyList p;
    p=C;
    while(p)
    {
        if(p->MusicType == n)
        {
            t=p;
            break;
        }
        p=p->next;
    }
    return OK;
}

void chooseClassification_I()
{
    cout<<"==============================="<<endl;
    cout<<"      请选择分类方式           "<<endl;
    cout<<"           (1)语种             "<<endl;
    cout<<"           (2)歌手             "<<endl;
    cout<<"           (3)风格             "<<endl;
    cout<<"==============================="<<endl;
}

int chooseClassification_II(int c)       //小
{
    int s;
    switch(c)
    {
    case 1:
        cout<<"-------------------------------"<<endl;
        cout<<"(1) 中文  (2)英文              "<<endl;
        cout<<"-------------------------------"<<endl;
        break;
    case 2:
        cout<<"-------------------------------"<<endl;
        cout<<"(1) 男歌手  (2)女歌手              "<<endl;
        cout<<"-------------------------------"<<endl;
        break;
    case 3:
        cout<<"-------------------------------"<<endl;
        cout<<"(1) 流行 (2)经典             "<<endl;
        cout<<"-------------------------------"<<endl;
        break;
    default:
        cout<<"输入有误"<<endl;
        break;
    }
    cin>>s;
    return s;
}

void adminMenu()
{
    system("cls");
    cout<<"=============欢迎进入管理员操作界面============="<<endl;
    cout<<"------------------------------------------------"<<endl;
    cout<<"--(1) 显示所有歌曲       (2)添加歌曲         ---"<<endl;
    cout<<"--(3) 删除歌曲           (4)查询歌曲总数     ---"<<endl;
    cout<<"--(5) 分类查询           (6)保存             ---"<<endl;
    cout<<"--(0)退出                                    ---"<<endl;
    cout<<"================================================"<<endl;
}

void addMusic_A(ClassifyList &C)            //添加歌曲
{
    MusicList temp;
    temp=C->next->mList;
    ElemType e;
    system("cls");
    cout<<"---请输入歌曲名:"<<endl;
    cin>>e.title;
    cout<<"---请输入歌手:"<<endl;
    cin>>e.author;
    for(int j=0; j<MAX_BCLASS; j++)
    {
        cout<<"---请输入类型:"<<endl;
        cin>>e.t[j];
    }
    getchar();
    cout<<"确认添加 [ y? or n?]"<<endl;
    char s;
    while(1)
    {
        cin>>s;
        if(s=='y' || s=='Y')
        {
            flag = 1;

            if(musicListAdd(temp,e))
            {
                cout<<"添加成功"<<endl;
                break;
            }
        }

        else if(s =='n' || s=='N')
            break;
        else
            cout<<"输入有误"<<endl;
    }
    getchar();
    cout<<"按回车返回"<<endl;
    getchar();
    adminOperation(C);
}

void delMusic_A(ClassifyList &C)
{
    system("cls");
    MusicList temp;
    temp = C->next->mList;
    ElemType e;
    printMusicList(temp);
    int s, c=0;
    char check;

    cout<<"请输入要删除歌曲的序号"<<endl;
    cin>>s;
    getElem(temp, s, e);
    cout<<"确定删除?"<<endl;
    while(1)
    {
        cout<<"[y or n?]"<<endl;
        cin>>check;
        if(check == 'y' ||check=='Y')
        {
            c=1;
            break;
        }
        else if(check == 'n' || check =='N')
            break;
        else
            cout<<"输入有误"<<endl;
    }
    if(c==1)
        if(musicListDelete(temp,s))
        {
            flag=1;
            cout<<"删除成功"<<endl;
        }

        else
            cout<<"删除失败,请正确输入"<<endl;
    getchar();
    cout<<"按回车返回"<<endl;
    getchar();
    adminOperation(C);
}

void musicNumber_A(ClassifyList &C)
{

    MusicList temp;
    temp = C->next->mList;
    int number ;
    number = totalMusicList(temp);
    system("cls");
    cout<<"歌曲总数为:"<<endl;
    cout<<number<<endl;
    getchar();
    cout<<"按回车返回"<<endl;
    getchar();
    adminOperation(C);
}

void musicShowAll_A(ClassifyList &C)
{
    MusicList temp;
    temp = C->next->mList;
    printMusicList(temp);
    char s;
    cout<<"按 'O' 返回"<<endl;
    while(1)
    {
        cin>>s;
        if(s=='O' || s=='o')
            break;
    }
    adminOperation(C);
}

void musicSave_A(ClassifyList &C)
{
    MusicList temp;
    temp = C->next->mList;
    system("cls");

    if(saveFile(temp, "music2"))
        cout<<"保存成功"<<endl;
    getchar();
    cout<<"按回车返回"<<endl;
    getchar();
    adminOperation(C);
}

void systemExit(ClassifyList &C)
{
    char check;
    if(flag == 1)
    {
        system("cls");
        cout<<"文件发生了修改,是否保存?"<<endl;
        cout<<"[y or n?]"<<endl;
        while(1)
        {
            cin>>check;
            if(check =='y' || check =='Y')
            {
                musicSave_A(C);
                break;
            }
            else if(check =='n'||check=='N')
                break;
            else
                cout<<"输入有误"<<endl;
        }
        exit(0);
    }
}

void musicClassificationShow(ClassifyList &C, MusicList &m)        //传入全部的曲目  ,返回一个当前类型的MusicList
{
    char s;
    ClassifyList temp=C->next, t;
    int k[MAX_BCLASS+1],t_k;
    int s_a,s_b;
    for(int j=0; j<MAX_BCLASS+1; j++)
        k[j] = 0;
    while(1)
    {
        chooseClassification_I();
        cin>>s_a;
        if(k[s_a] == 0)
        {

            addDisClassifyList(temp, s_a);   //根据s_a 添加分类

            s_b=chooseClassification_II(s_a);       //选择小类 返回选择
            disClassificationOperation(temp->classification, s_b, t);   // 返回结果t
            temp=t;

            printMusicList(temp->mList);
            k[s_a] = 1;

        }
        cout<<"输入 'O' 结束删选"<<endl;
        cout<<"任意键继续"<<endl;
        cin>>s;
        if(s=='O' || s=='o')
            break;
    }
    m=temp->mList;
}

void musicClassificationShow_A(ClassifyList &C)      //管理员下的分类查看查看
{
    MusicList m;
    system("cls");                                 //管理员下，此m无意义，用以占位
    musicClassificationShow(C,m);
    adminOperation(C);
}

void adminOperation(ClassifyList &C)
{
    system("cls");
    adminMenu();
    int choice;
    cout<<"请输入选择："<<endl;
    cin>>choice;
    switch(choice)
    {
    case 1:
        musicShowAll_A(C);
        break;
    case 2:
        addMusic_A(C);
        break;
    case 3:
        delMusic_A(C);
        break;
    case 4:
        musicNumber_A(C);
        break;
    case 5:
        musicClassificationShow_A(C);
        break;
    case 6:
        musicSave_A(C);
        break;
    case 0:
        systemExit(C);
        break;
    }
}

void main_A()
{
    cout<<"数据加载中"<<endl;
    MusicList M;
    ClassifyList C;
    initMusicList(M);
    initClassifyList(C,0);
    loadFile(M);
    addMusicToClassification(C,M,0,0);
    adminOperation(C);
}

/*****************************************************************************************/
/**********customer**********************************************************************/

typedef struct
{
    ElemType *base;
    int Front;
    int Rear;
} SqQueue;

Status initQueue(SqQueue &Q)
{
    Q.base = new ElemType[MAXQSIZE];
    if(!Q.base)
        exit(ERROR);
    Q.Front=Q.Rear=0;
    return OK;
}

int queueLength(SqQueue Q)   //队列长度
{
    return (Q.Rear-Q.Front+MAXQSIZE)%MAXQSIZE;
}

Status enQueue(SqQueue &Q, ElemType e)   //入队
{
    if((Q.Rear+1)%MAXQSIZE == Q.Front)      //队满
        return ERROR;
    Q.base[Q.Rear]=e;
    Q.Rear=(Q.Rear+1) %MAXQSIZE;            //队尾加1;
    return OK;
}

Status deQueue(SqQueue &Q, ElemType &e)     //出队
{
    if(Q.Front==Q.Rear)         //队空
        return ERROR;
    e=Q.base[Q.Front];
    Q.Front=(Q.Front+1) % MAXQSIZE; //队头加1
    return OK;
}

void customeOperation(SqQueue &Q, ClassifyList C);

void custmeMune()
{
    cout<<"-------------欢迎使用--------------------"<<endl;
    cout<<"---(1)普通点歌           (2)分类点歌  ---"<<endl;
    cout<<"---(3)切歌               (4)置顶      ---"<<endl;
    cout<<"---(5)查看已点歌曲       (6)查看歌曲数---"<<endl;
    cout<<"---(7)查看当前播放       (8)删除歌曲  ---"<<endl;
    cout<<"---(0)退出                            ---"<<endl;
    cout<<"-----------------------------------------"<<endl;
}

void orderMusic_B_I(SqQueue &Q, ClassifyList C)   //根据所有歌单点歌
{
    system("cls");
    MusicList temp;
    temp = C->next->mList;
    printMusicList(temp);
    ElemType e;
    int i;
    char check;
    cout<<"请选择要添加的歌曲，输入索引"<<endl;
    while(1)
    {
        cin>>i;
        if(getElem(temp, i,e))
        {

            if(enQueue(Q,e))
                cout<<"添加成功"<<endl;
            else
            {
                cout<<"添加失败"<<endl;
                cout<<"列表已满"<<endl;
            }
        }
        else

            cout<<"添加失败"<<endl;



        cout<<""<<endl;
        cout<<"按 'O' 返回，其他继续"<<endl;
        cin>>check;
        if(check=='o'||check=='O')
            break;
        cout<<"请选择要添加的歌曲，输入索引"<<endl;
    }
    getchar();
    cout<<"--歌曲添加完成--"<<endl;
    cout<<"按回车返回"<<endl;
    customeOperation(Q,C);
}

void orderMusic_B_II(SqQueue &Q, ClassifyList C)    //根据分类 点歌
{
    MusicList m;
    system("cls");
    int i;
    char check;
    ElemType e;
    musicClassificationShow(C, m);
    cout<<"请选择要添加的歌曲，输入索引"<<endl;
    while(1)
    {
        cin>>i;
        if(getElem(m, i,e))
        {
            cout<<"添加成功"<<endl;
            enQueue(Q,e);
        }
        else
            cout<<"添加失败"<<endl;
        cout<<""<<endl;
        cout<<"按 'O' 返回，其他继续"<<endl;
        cin>>check;
        if(check=='o'||check=='O')
            break;
        cout<<"请选择要添加的歌曲，输入索引"<<endl;
    }
    getchar();
    cout<<"--歌曲添加完成--"<<endl;
    cout<<"按回车返回"<<endl;
    getchar();
    customeOperation(Q,C);
}


Status showSqMusic(SqQueue &Q)   //c:索引，p:返回队列中的位置
{
    int t;
    int i;
    int j;
    int index=1;
    t=Q.Rear - Q.Front;
    system("cls");
    if(t == 0)
    {
        cout<<"列表中无歌曲"<<endl;
        return ERROR;
    }

    else if(t>0)
    {
        for(i=Q.Front; i<Q.Rear; i++)
        {
            cout<<index<<" :"<<Q.base[i].title<<"     ---"<<Q.base[i].author<<endl;
            ++index;
        }
    }
    else
    {
        for(i=Q.Front; i<MAXQSIZE; i++)
        {
            cout<<index<<" :"<<Q.base[i].title<<"     ---"<<Q.base[i].author<<endl;
            ++index;
        }
//        cout<<"---test--"<<endl;
        for(j=0; j<Q.Rear; j++)
        {
            cout<<index<<" :"<<Q.base[j].title<<"     ---"<<Q.base[j].author<<endl;
            ++index;
        }
    }
    return OK;
}

Status showAllMusic_B(SqQueue &Q, ClassifyList C)
{
    system("cls");
    if(Q.Front==Q.Rear)
        cout<<"列表中没有歌曲"<<endl;
    else
    {
        showSqMusic(Q);
    }
    getchar();
    cout<<""<<endl;
    cout<<"按回车返回"<<endl;
    getchar();
    customeOperation(Q,C);
}

Status localteSqMusic(SqQueue &Q, int c, int &p)     //找到歌曲在列表中的位置
{
    int t;
    int i;
    int j;
    int index=1;
    t=Q.Rear - Q.Front;
    {
        if(t == 0)
        {
            cout<<"列表中无歌曲"<<endl;
            return ERROR;
        }

        else if(t>0)
        {
            for(i=Q.Front; i<Q.Rear; i++)
            {
                if(c==index)
                    p=i;
                ++index;
            }
        }
        else
        {
            for(i=Q.Front; i<MAXQSIZE; i++)
            {
                if(c==index)
                    p=i;
                ++index;
            }
            for(j=0; i<Q.Rear; j++)
            {
                if(c==index)
                    p=i;
                ++index;
            }
        }
    }
    return OK;
}

void changeMusic_B(SqQueue &Q,ClassifyList C)     //切歌
{
    system("cls");
    ElemType temp;

    if(Q.Front==Q.Rear)
        cout<<"列表中没有歌曲"<<endl;
    else
    {
        if(deQueue(Q,temp))
        {
            pM=temp;
            cout<<"切歌成功"<<endl;
        }
        else
        {
            cout<<"切歌失败"<<endl;
        }
    }
    cout<<""<<endl;
    getchar();
    cout<<"按回车返回"<<endl;
    getchar();
    customeOperation(Q,C);
}

void stickMusic_B(SqQueue &Q, ClassifyList C)      //置顶
{
    ElemType temp;
    SqQueue t_Q;
    system("cls");
    int c,p;
    if(Q.Front==Q.Rear)
        cout<<"列表中没有歌曲"<<endl;
    else
    {
        showSqMusic(Q);
        cout<<"请选择要置顶的歌曲，输入索引"<<endl;
        cin>>c;
        int t;
        int i;
        int j;
        int index=1;
        t=Q.Rear - Q.Front;

        system("cls");
        localteSqMusic(Q,c,p);              //找到索引在队列中的位置

        temp = Q.base[p];

        /**************/
        //置顶
        if(t>0)                             //Q.Frong 在Q.Rear 下面
        {
            for(i=p; i>Q.Front; i--)
                Q.base[i]=Q.base[i-1];
        }

        else if(t<0)                    //Q.Frong 在Q.Rear 下面
        {
            if(p>Q.Front)                   //p在Front下面
            {
                for(i=p; i>Q.Front; i--)
                    Q.base[i]=Q.base[i-1];
            }

            else if(p<Q.Rear)               //p在Rear上面
            {
                for(i=0; i<p; i++)
                    Q.base[i+1]=Q.base[i];

                Q.base[0]=Q.base[MAXQSIZE-1];

                for(i=p; i>Q.Front; i--)
                    Q.base[i]=Q.base[i-1];
            }
        }
        /***************************/

        Q.base[Q.Front]=temp;

        getchar();
        cout<<""<<endl;
        cout<<"---置顶完成---"<<endl;
    }
    getchar();
    cout<<"按回车返回"<<endl;
    getchar();
    customeOperation(Q,C);
}

void totalMusic_B(SqQueue &Q,ClassifyList C)        //歌曲数
{
    system("cls");
    cout<<"当前歌曲列表中数量："<<endl;
    cout<<queueLength(Q)<<endl;
    cout<<""<<endl;
    getchar();
    cout<<"按回车返回"<<endl;
    getchar();
    customeOperation(Q,C);
}

void playingMusic_B(SqQueue &Q,ClassifyList C)
{
    system("cls");
    cout<<""<<endl;
    cout<<"当前播放："<<endl;
    cout<<"  →"<<pM.title<<"   ---"<<pM.author<<"←"<<endl;
    cout<<""<<endl;
    cout<<""<<endl;
    getchar();
    cout<<"按回车返回"<<endl;
    getchar();
    customeOperation(Q,C);
}

void deleteMusic_B(SqQueue &Q, ClassifyList C)                //删除曲目
{
    system("cls");
    if(Q.Front==Q.Rear)
        cout<<"列表中没有歌曲"<<endl;
    else
    {


        showSqMusic(Q);
        int c,p;
        cout<<""<<endl;
        cout<<"请选择要删除的曲目,输入索引"<<endl;
        cin>>c;
        int t;
        int i;
        int j;
        int index=1;
        t=Q.Rear - Q.Front;
        localteSqMusic(Q,c,p);   //找到索引在队列中的位置

        /**************/
        if(t>0)                             //Q.Frong 在Q.Rear 下面
        {
            for(i=p; i>Q.Front; i--)
                Q.base[i]=Q.base[i-1];
        }

        else if(t<0)                    //Q.Frong 在Q.Rear 下面
        {
            if(p>Q.Front)                   //p在Front下面
            {
                for(i=p; i>Q.Front; i--)
                    Q.base[i]=Q.base[i-1];
            }

            else if(p<Q.Rear)               //p在Rear上面
            {
                for(i=0; i<p; i++)
                    Q.base[i+1]=Q.base[i];

                Q.base[0]=Q.base[MAXQSIZE-1];

                for(i=p; i>Q.Front; i--)
                    Q.base[i]=Q.base[i-1];
            }
        }
        /**************/
        Q.Front=(Q.Front+1)%MAXQSIZE;
        system("cls");
        cout<<"删除成功"<<endl;
    }
    getchar();
    cout<<"按回车返回"<<endl;
    getchar();
    customeOperation(Q,C);
}

void customeOperation(SqQueue &Q, ClassifyList C)
{
    system("cls");
    custmeMune();
    int s;
    int t;
    cout<<"请选择操作"<<endl;
    cin>>s;
    switch(s)
    {
    case 1:
        orderMusic_B_I(Q,C);
        break;
    case 2:
        orderMusic_B_II(Q,C);
        break;
    case 3:
        changeMusic_B(Q,C);
        break;
    case 4:
        stickMusic_B(Q,C);
        break;
    case 5:
        showAllMusic_B(Q,C);     //0,t无意义
        break;
    case 6:
        totalMusic_B(Q,C);
        break;
    case 7:
        playingMusic_B(Q,C);
        break;
    case 8:
        deleteMusic_B(Q,C);
        break;
    case 0:
        exit(0);
    default:
        cout<<"输入有误"<<endl;
        break;
    }
}

void main_B()
{
    cout<<"数据加载中"<<endl;
    MusicList M;
    ClassifyList C;
    SqQueue Q;
    initQueue(Q);
    initMusicList(M);
    initClassifyList(C,0);
    loadFile(M);
    addMusicToClassification(C,M,0,0);
    strcpy(pM.title, "默认背景音乐");
    strcpy(pM.author, "XXX");
    customeOperation(Q,C);
}

void loginMenu()
{
    cout<<"=============请选择使用身份============="<<endl;
    int n;
    cout<<"----(1) 管理员----"<<endl;
    cout<<"----(2) 顾客------"<<endl;
    cout<<"请选择："<<endl;
    cin>>n;
    switch(n)
    {
    case 1:
        if(loginAdmin())
            main_A();
        else
            cout<<"密码错误"<<endl;
        break;
    case 2:
        main_B();
        break;
    default:
        cout<<"输入有误"<<endl;
        break;
    }
}

int main()
{

loginMenu();
    return 0;
}
