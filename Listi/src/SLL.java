
public class SLL<E> {
private Node<E> first;
public SLL() {
	this.first=null;
}


//mozni funkcii
/*public void insertFirst(E o, Node<E> node) {
	Node<E> ins = new Node<E>(first,o);
}*/
public void inserAfter(E o, Node<E> node) {
	if(node!=null) {
		Node<E> ins = new Node<E>(node.sleden,o);
		node.sleden = ins;
	}
};
//public void insertBefore() {};
public void removeFirst() {
	if(first != null)
	first=first.sleden;
};

/*public E remove(Node<E> node) {
if(first != null) {
	Node<E> tmp = first;
	while(tmp.sleden!=node && tmp.sleden.sleden != null) {
		tmp=tmp.sleden;
		
	}
	if(tmp.sleden==node) {
		tmp.sleden=tmp.sleden.sleden;
		return node.data;
	}
	//else throw exception my dude
}

//else throw exception my dude
}
}*/
//insert last
public void insertLast(E o) {
	if(first != null )
{
		Node<E> tmp = first;
		while(tmp.sleden != null) {
			tmp = tmp.sleden;
		}
		Node<E> ins = new Node<E>(null, o);
		tmp.sleden = ins;
		
			}
	else insertLast(0);
}
}