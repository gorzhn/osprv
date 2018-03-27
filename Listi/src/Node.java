
public class Node<E> {
protected E data;
protected Node<E> sleden;
public E getData() {
	return data;
	}
public Node<E> getSleden() {
	return sleden;
}
public void setSleden(Node<E> sleden) {
	this.sleden = sleden;
}
public void setData(E data) {
	this.data = data;
	}
public Node(Node<E> sled, E data )
{
	this.sleden = sled;
	this.data = data;
	
	}

}
//O -> O -> O -> O    
//            