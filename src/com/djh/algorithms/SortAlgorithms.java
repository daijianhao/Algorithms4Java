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
		
		sortData = insertSort(data);
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
}


