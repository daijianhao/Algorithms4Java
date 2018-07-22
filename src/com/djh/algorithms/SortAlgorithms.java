package com.djh.algorithms;

/**
 * 实现排序算法的类
 * @author 代
 *
 */
public class SortAlgorithms {
	
	public static void main(String[] args) {
		int[] data={5,3,8,7,5,6,2,1,4,12,89,55,34,51,61,20,78,1025,36};//测试数据
		int[] sortData;
		
//		int[] sortData = selectSort(data);
//		print(sortData);
		
	/*	sortData = insertSort(data);
		print(sortData);*/
		sortData = shellSort(data);
		print(sortData);
	}
	
	/**
	 * 打印排序后的数组
	 * @param data
	 */
	public static void print(int[] data){
		for(int elem:data){
			System.out.print(elem+" ");
		}
		System.out.println("\n");
	}

	/**
	 * 快速排序（从小到大）
	 * @param data 需要排序的整型数组
	 * @return 排序完成的数据
	 */
	public static int[] selectSort(int[] data){
		int minIndex;
		for(int i=0;i<data.length;++i){
			minIndex=i;
			for(int j=i;j<data.length;++j){
				if(data[j]<data[minIndex]){
					minIndex=j;
				}
			}
			if(data[i]!=data[minIndex]){
				data[i]=data[i]+data[minIndex];
				data[minIndex]=data[i]-data[minIndex];
				data[i]=data[i]-data[minIndex];
			}
		}
		return data;
	}
	
	/**
	 * 插入排序
	 * @param data
	 * @return
	 */
	public static int[] insertSort(int[] data){
		for(int i=1;i<data.length;++i){
			for(int j=i;j>0&&data[j]<data[j-1];j--){
				data[j]=data[j]+data[j-1];
				data[j-1]=data[j]-data[j-1];
				data[j]=data[j]-data[j-1];
			}
		}
		return data;
	}
	
	/**
	 * 希尔排序
	 * @param datas
	 * @return
	 */
	public static int[] shellSort(int[] data){
		int N=data.length;
		int h=1;
		while(h<N/3){
			//构造递减序列
			h=3*h+1;
		}
		while(h>=1){
			for(int i=h;i<N;i++){
				//将data[i]插入到data[i-h]、data[i-2*h].data[i-3*h]中，此处运用的了插入排序的思想
				for(int j=i;j>=h&&data[j]<data[j-h];j-=h){
					data[j]=data[j]+data[j-h];
					data[j-h]=data[j]-data[j-h];
					data[j]=data[j]-data[j-h];
				}
			}
			//每排完一个h序列，h=h/3
			h=h/3;
		}
		return data;
	}
}


private static int[] aux;
	
	
	/**
	 * 自顶向下的归并排序
	 * @param data
	 */
	public static void sortTopToBottom(int[] data){
		aux=new int[data.length];
		sort(data,0,data.length-1);
	}
	
	
	/**
	 * 自底向上的归并排序
	 * @param data
	 */
	public static void sortBottomToTop(int[] data){
		aux=new int[data.length];
		final int N=data.length;
		for(int sz=1;sz<N;sz=sz+sz){//sz表示子数组的长度，从1开始
			for(int lo=0;lo<N-sz;lo+=(sz+sz)){
				//考虑到元素个数不是偶数个时，最后一个子数组的长度会比sz小，所以索引的最大值可能取N-1
				merge(data, lo, lo+sz-1,Math.min(lo+sz+sz-1, N-1));
			}
		}
	}
	
	public static void sort(int[] data,int lo,int hi){
		//将数组data[lo...hi]排序
		if(hi<=lo){
			return;
		}
		int mid=lo+(hi-lo)/2;
		sort(data,lo,mid);
		sort(data,mid+1,hi);
		merge(data,lo,mid,hi);
	}
	
	/**
	 * 归并两个数组
	 * @param data
	 * @param lo
	 * @param mid
	 * @param hi
	 */
	public static void merge(int[] data,int lo,int mid,int hi){
		//将data[lo...mid]和data[mid+1...hi]归并
		int i=lo,j=mid+1;
		for(int k=lo;k<=hi;++k){//将data[lo...hi]复制到aux[lo...hi]中
			aux[k]=data[k];
		}
		for(int k=lo;k<=hi;++k){
			if(i>mid){
				data[k]=aux[j++];
			}else if(j>hi){
				data[k]=aux[i++];
			}else if(aux[j]<aux[i]){
				data[k]=aux[j++];
			}else {
				data[k]=aux[i++];
			}
		}
	}
	
	
	/**
	 * 快速排序
	 * @param data
	 */
	public static void quickSort(int[] data){
		subMethod4sort(data, 0, data.length-1);
	}
	
	public static void subMethod4sort(int[] data,int lo,int hi){
		if(hi<=lo){
			return;
		}
		int j=partition(data,lo,hi);
		subMethod4sort(data, lo, j-1);
		subMethod4sort(data, j+1, hi);
	}
	
	/**
	 * 快速排序找到切入点
	 * @param data
	 * @param lo
	 * @param hi
	 * @return
	 */
	private static int partition(int[] data, int lo, int hi) {
		int i=lo;
		int j=hi+1;
		int v=data[lo];
		while(true){
			while(data[++i]<v){
				if(i==hi){
					break;
				}
			}
			while(data[--j]>v){
				if(j==lo){
					break;
				}
			}
			if(i>=j)
			{
				break;
			}
			exch(data,i,j);
		}
		exch(data,j,lo);
		return j;
	}
	
	private static void exch(int[] data,int i,int j){
		int temp=data[i];
		data[i]=data[j];
		data[j]=temp;
	}
	
	/**
	 * 堆排序
	 * @param data
	 */
	public static void heapSort(int[] data){
		createHeap(data,data.length);
	}
	
	private static void createHeap(int[] data,int length){
		if(length<=1){
			return;
		}
		//构造堆
		for(int i=length/2-1;i>=0;--i){
			adjust(data,i,length);
		}
		//将堆顶元素与最后一个元素交换
		exch(data,0,--length);
		createHeap(data, length);
	}
	
	/**
	 * 对一个非叶子节点及其两个子节点进行调整，使最大元素在上面
	 * @param data
	 * @param index 非叶子节点索引+1
	 */
	private static void adjust(int[] data,int index,int length){
		int child1=index*2+1;
		int child2=child1+1;
		int temp;
		if(child1<length&&data[child1]>data[index]){
			temp=data[index];
			data[index]=data[child1];
			data[child1]=temp;
			adjust(data, child1,length);
		}
		if(child2<length&&data[child2]>data[index]){
			temp=data[index];
			data[index]=data[child2];
			data[child2]=temp;
			adjust(data, child2,length);
		}
	}
}
