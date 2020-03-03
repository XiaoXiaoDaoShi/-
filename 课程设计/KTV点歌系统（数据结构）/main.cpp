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
    while(1)         //�ֶζ�ȡ�ļ�
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
        e.t[2]=atoi(z);           //�ӵ��ṹ��ElemType��

        q->data=e;
        q->next=NULL;
        p->next=q;      //q����β���
        p=q;
        //pָ���µ�β���
    }
    fclose(fp);
}

Status saveFile(MusicList &M, string filename)   //filenameʡ��.txt �������ļ���
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
    cout<<"���������Ա����"<<endl;
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

Status addMusicToClassification(ClassifyList &C, MusicList M, int i, int n)   //���Music�е�ClassifyList  i,n���classicaton �����ݲ�������
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

Status disClassification(ClassifyList C,int i, int n, MusicList &m)    //�Ե�ǰ�б����,�����µ��б�m  i,n��������
{
    // i: 1,2
    if(C->c==i)         //�ѷֹ�
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

Status addDisClassifyList(ClassifyList &C, int i)  //����i�������classify����
{
    //i:1,2,3
    ClassifyList new_C;
    initClassifyList(new_C,i);

    MusicList m;
    initMusicList(m);

    for(int n=1; n<=MAX_SCLASS; n++)           //���ݴ��࣬����С����new_C��
    {
        initMusicList(m);

        disClassification(C,i,n,m);

        addMusicToClassification(new_C,m,i,n);
    }

    addClassification(C,new_C);                 //Cָ����new_C
}

Status disClassificationOperation(ClassifyList C,int n, ClassifyList &t)   //����С���ͷ������������һ���ֺõ�����
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
    cout<<"      ��ѡ����෽ʽ           "<<endl;
    cout<<"           (1)����             "<<endl;
    cout<<"           (2)����             "<<endl;
    cout<<"           (3)���             "<<endl;
    cout<<"==============================="<<endl;
}

int chooseClassification_II(int c)       //С
{
    int s;
    switch(c)
    {
    case 1:
        cout<<"-------------------------------"<<endl;
        cout<<"(1) ����  (2)Ӣ��              "<<endl;
        cout<<"-------------------------------"<<endl;
        break;
    case 2:
        cout<<"-------------------------------"<<endl;
        cout<<"(1) �и���  (2)Ů����              "<<endl;
        cout<<"-------------------------------"<<endl;
        break;
    case 3:
        cout<<"-------------------------------"<<endl;
        cout<<"(1) ���� (2)����             "<<endl;
        cout<<"-------------------------------"<<endl;
        break;
    default:
        cout<<"��������"<<endl;
        break;
    }
    cin>>s;
    return s;
}

void adminMenu()
{
    system("cls");
    cout<<"=============��ӭ�������Ա��������============="<<endl;
    cout<<"------------------------------------------------"<<endl;
    cout<<"--(1) ��ʾ���и���       (2)��Ӹ���         ---"<<endl;
    cout<<"--(3) ɾ������           (4)��ѯ��������     ---"<<endl;
    cout<<"--(5) �����ѯ           (6)����             ---"<<endl;
    cout<<"--(0)�˳�                                    ---"<<endl;
    cout<<"================================================"<<endl;
}

void addMusic_A(ClassifyList &C)            //��Ӹ���
{
    MusicList temp;
    temp=C->next->mList;
    ElemType e;
    system("cls");
    cout<<"---�����������:"<<endl;
    cin>>e.title;
    cout<<"---���������:"<<endl;
    cin>>e.author;
    for(int j=0; j<MAX_BCLASS; j++)
    {
        cout<<"---����������:"<<endl;
        cin>>e.t[j];
    }
    getchar();
    cout<<"ȷ����� [ y? or n?]"<<endl;
    char s;
    while(1)
    {
        cin>>s;
        if(s=='y' || s=='Y')
        {
            flag = 1;

            if(musicListAdd(temp,e))
            {
                cout<<"��ӳɹ�"<<endl;
                break;
            }
        }

        else if(s =='n' || s=='N')
            break;
        else
            cout<<"��������"<<endl;
    }
    getchar();
    cout<<"���س�����"<<endl;
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

    cout<<"������Ҫɾ�����������"<<endl;
    cin>>s;
    getElem(temp, s, e);
    cout<<"ȷ��ɾ��?"<<endl;
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
            cout<<"��������"<<endl;
    }
    if(c==1)
        if(musicListDelete(temp,s))
        {
            flag=1;
            cout<<"ɾ���ɹ�"<<endl;
        }

        else
            cout<<"ɾ��ʧ��,����ȷ����"<<endl;
    getchar();
    cout<<"���س�����"<<endl;
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
    cout<<"��������Ϊ:"<<endl;
    cout<<number<<endl;
    getchar();
    cout<<"���س�����"<<endl;
    getchar();
    adminOperation(C);
}

void musicShowAll_A(ClassifyList &C)
{
    MusicList temp;
    temp = C->next->mList;
    printMusicList(temp);
    char s;
    cout<<"�� 'O' ����"<<endl;
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
        cout<<"����ɹ�"<<endl;
    getchar();
    cout<<"���س�����"<<endl;
    getchar();
    adminOperation(C);
}

void systemExit(ClassifyList &C)
{
    char check;
    if(flag == 1)
    {
        system("cls");
        cout<<"�ļ��������޸�,�Ƿ񱣴�?"<<endl;
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
                cout<<"��������"<<endl;
        }
        exit(0);
    }
}

void musicClassificationShow(ClassifyList &C, MusicList &m)        //����ȫ������Ŀ  ,����һ����ǰ���͵�MusicList
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

            addDisClassifyList(temp, s_a);   //����s_a ��ӷ���

            s_b=chooseClassification_II(s_a);       //ѡ��С�� ����ѡ��
            disClassificationOperation(temp->classification, s_b, t);   // ���ؽ��t
            temp=t;

            printMusicList(temp->mList);
            k[s_a] = 1;

        }
        cout<<"���� 'O' ����ɾѡ"<<endl;
        cout<<"���������"<<endl;
        cin>>s;
        if(s=='O' || s=='o')
            break;
    }
    m=temp->mList;
}

void musicClassificationShow_A(ClassifyList &C)      //����Ա�µķ���鿴�鿴
{
    MusicList m;
    system("cls");                                 //����Ա�£���m�����壬����ռλ
    musicClassificationShow(C,m);
    adminOperation(C);
}

void adminOperation(ClassifyList &C)
{
    system("cls");
    adminMenu();
    int choice;
    cout<<"������ѡ��"<<endl;
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
    cout<<"���ݼ�����"<<endl;
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

int queueLength(SqQueue Q)   //���г���
{
    return (Q.Rear-Q.Front+MAXQSIZE)%MAXQSIZE;
}

Status enQueue(SqQueue &Q, ElemType e)   //���
{
    if((Q.Rear+1)%MAXQSIZE == Q.Front)      //����
        return ERROR;
    Q.base[Q.Rear]=e;
    Q.Rear=(Q.Rear+1) %MAXQSIZE;            //��β��1;
    return OK;
}

Status deQueue(SqQueue &Q, ElemType &e)     //����
{
    if(Q.Front==Q.Rear)         //�ӿ�
        return ERROR;
    e=Q.base[Q.Front];
    Q.Front=(Q.Front+1) % MAXQSIZE; //��ͷ��1
    return OK;
}

void customeOperation(SqQueue &Q, ClassifyList C);

void custmeMune()
{
    cout<<"-------------��ӭʹ��--------------------"<<endl;
    cout<<"---(1)��ͨ���           (2)������  ---"<<endl;
    cout<<"---(3)�и�               (4)�ö�      ---"<<endl;
    cout<<"---(5)�鿴�ѵ����       (6)�鿴������---"<<endl;
    cout<<"---(7)�鿴��ǰ����       (8)ɾ������  ---"<<endl;
    cout<<"---(0)�˳�                            ---"<<endl;
    cout<<"-----------------------------------------"<<endl;
}

void orderMusic_B_I(SqQueue &Q, ClassifyList C)   //�������и赥���
{
    system("cls");
    MusicList temp;
    temp = C->next->mList;
    printMusicList(temp);
    ElemType e;
    int i;
    char check;
    cout<<"��ѡ��Ҫ��ӵĸ�������������"<<endl;
    while(1)
    {
        cin>>i;
        if(getElem(temp, i,e))
        {

            if(enQueue(Q,e))
                cout<<"��ӳɹ�"<<endl;
            else
            {
                cout<<"���ʧ��"<<endl;
                cout<<"�б�����"<<endl;
            }
        }
        else

            cout<<"���ʧ��"<<endl;



        cout<<""<<endl;
        cout<<"�� 'O' ���أ���������"<<endl;
        cin>>check;
        if(check=='o'||check=='O')
            break;
        cout<<"��ѡ��Ҫ��ӵĸ�������������"<<endl;
    }
    getchar();
    cout<<"--����������--"<<endl;
    cout<<"���س�����"<<endl;
    customeOperation(Q,C);
}

void orderMusic_B_II(SqQueue &Q, ClassifyList C)    //���ݷ��� ���
{
    MusicList m;
    system("cls");
    int i;
    char check;
    ElemType e;
    musicClassificationShow(C, m);
    cout<<"��ѡ��Ҫ��ӵĸ�������������"<<endl;
    while(1)
    {
        cin>>i;
        if(getElem(m, i,e))
        {
            cout<<"��ӳɹ�"<<endl;
            enQueue(Q,e);
        }
        else
            cout<<"���ʧ��"<<endl;
        cout<<""<<endl;
        cout<<"�� 'O' ���أ���������"<<endl;
        cin>>check;
        if(check=='o'||check=='O')
            break;
        cout<<"��ѡ��Ҫ��ӵĸ�������������"<<endl;
    }
    getchar();
    cout<<"--����������--"<<endl;
    cout<<"���س�����"<<endl;
    getchar();
    customeOperation(Q,C);
}


Status showSqMusic(SqQueue &Q)   //c:������p:���ض����е�λ��
{
    int t;
    int i;
    int j;
    int index=1;
    t=Q.Rear - Q.Front;
    system("cls");
    if(t == 0)
    {
        cout<<"�б����޸���"<<endl;
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
        cout<<"�б���û�и���"<<endl;
    else
    {
        showSqMusic(Q);
    }
    getchar();
    cout<<""<<endl;
    cout<<"���س�����"<<endl;
    getchar();
    customeOperation(Q,C);
}

Status localteSqMusic(SqQueue &Q, int c, int &p)     //�ҵ��������б��е�λ��
{
    int t;
    int i;
    int j;
    int index=1;
    t=Q.Rear - Q.Front;
    {
        if(t == 0)
        {
            cout<<"�б����޸���"<<endl;
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

void changeMusic_B(SqQueue &Q,ClassifyList C)     //�и�
{
    system("cls");
    ElemType temp;

    if(Q.Front==Q.Rear)
        cout<<"�б���û�и���"<<endl;
    else
    {
        if(deQueue(Q,temp))
        {
            pM=temp;
            cout<<"�и�ɹ�"<<endl;
        }
        else
        {
            cout<<"�и�ʧ��"<<endl;
        }
    }
    cout<<""<<endl;
    getchar();
    cout<<"���س�����"<<endl;
    getchar();
    customeOperation(Q,C);
}

void stickMusic_B(SqQueue &Q, ClassifyList C)      //�ö�
{
    ElemType temp;
    SqQueue t_Q;
    system("cls");
    int c,p;
    if(Q.Front==Q.Rear)
        cout<<"�б���û�и���"<<endl;
    else
    {
        showSqMusic(Q);
        cout<<"��ѡ��Ҫ�ö��ĸ�������������"<<endl;
        cin>>c;
        int t;
        int i;
        int j;
        int index=1;
        t=Q.Rear - Q.Front;

        system("cls");
        localteSqMusic(Q,c,p);              //�ҵ������ڶ����е�λ��

        temp = Q.base[p];

        /**************/
        //�ö�
        if(t>0)                             //Q.Frong ��Q.Rear ����
        {
            for(i=p; i>Q.Front; i--)
                Q.base[i]=Q.base[i-1];
        }

        else if(t<0)                    //Q.Frong ��Q.Rear ����
        {
            if(p>Q.Front)                   //p��Front����
            {
                for(i=p; i>Q.Front; i--)
                    Q.base[i]=Q.base[i-1];
            }

            else if(p<Q.Rear)               //p��Rear����
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
        cout<<"---�ö����---"<<endl;
    }
    getchar();
    cout<<"���س�����"<<endl;
    getchar();
    customeOperation(Q,C);
}

void totalMusic_B(SqQueue &Q,ClassifyList C)        //������
{
    system("cls");
    cout<<"��ǰ�����б���������"<<endl;
    cout<<queueLength(Q)<<endl;
    cout<<""<<endl;
    getchar();
    cout<<"���س�����"<<endl;
    getchar();
    customeOperation(Q,C);
}

void playingMusic_B(SqQueue &Q,ClassifyList C)
{
    system("cls");
    cout<<""<<endl;
    cout<<"��ǰ���ţ�"<<endl;
    cout<<"  ��"<<pM.title<<"   ---"<<pM.author<<"��"<<endl;
    cout<<""<<endl;
    cout<<""<<endl;
    getchar();
    cout<<"���س�����"<<endl;
    getchar();
    customeOperation(Q,C);
}

void deleteMusic_B(SqQueue &Q, ClassifyList C)                //ɾ����Ŀ
{
    system("cls");
    if(Q.Front==Q.Rear)
        cout<<"�б���û�и���"<<endl;
    else
    {


        showSqMusic(Q);
        int c,p;
        cout<<""<<endl;
        cout<<"��ѡ��Ҫɾ������Ŀ,��������"<<endl;
        cin>>c;
        int t;
        int i;
        int j;
        int index=1;
        t=Q.Rear - Q.Front;
        localteSqMusic(Q,c,p);   //�ҵ������ڶ����е�λ��

        /**************/
        if(t>0)                             //Q.Frong ��Q.Rear ����
        {
            for(i=p; i>Q.Front; i--)
                Q.base[i]=Q.base[i-1];
        }

        else if(t<0)                    //Q.Frong ��Q.Rear ����
        {
            if(p>Q.Front)                   //p��Front����
            {
                for(i=p; i>Q.Front; i--)
                    Q.base[i]=Q.base[i-1];
            }

            else if(p<Q.Rear)               //p��Rear����
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
        cout<<"ɾ���ɹ�"<<endl;
    }
    getchar();
    cout<<"���س�����"<<endl;
    getchar();
    customeOperation(Q,C);
}

void customeOperation(SqQueue &Q, ClassifyList C)
{
    system("cls");
    custmeMune();
    int s;
    int t;
    cout<<"��ѡ�����"<<endl;
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
        showAllMusic_B(Q,C);     //0,t������
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
        cout<<"��������"<<endl;
        break;
    }
}

void main_B()
{
    cout<<"���ݼ�����"<<endl;
    MusicList M;
    ClassifyList C;
    SqQueue Q;
    initQueue(Q);
    initMusicList(M);
    initClassifyList(C,0);
    loadFile(M);
    addMusicToClassification(C,M,0,0);
    strcpy(pM.title, "Ĭ�ϱ�������");
    strcpy(pM.author, "XXX");
    customeOperation(Q,C);
}

void loginMenu()
{
    cout<<"=============��ѡ��ʹ�����============="<<endl;
    int n;
    cout<<"----(1) ����Ա----"<<endl;
    cout<<"----(2) �˿�------"<<endl;
    cout<<"��ѡ��"<<endl;
    cin>>n;
    switch(n)
    {
    case 1:
        if(loginAdmin())
            main_A();
        else
            cout<<"�������"<<endl;
        break;
    case 2:
        main_B();
        break;
    default:
        cout<<"��������"<<endl;
        break;
    }
}

int main()
{

loginMenu();
    return 0;
}
