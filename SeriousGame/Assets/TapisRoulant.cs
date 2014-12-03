using UnityEngine;
using System.Collections;

public class TapisRoulant : MonoBehaviour {

	public Rigidbody objet;
	public Transform origin;
	
	void Start(){
		StartCoroutine ("Generate");
	}

	private IEnumerator Generate(){

		while (true) {
			yield return new WaitForSeconds (5f);
			Rigidbody instance;	
			instance = Instantiate (objet, origin.position, origin.rotation) as Rigidbody;
			instance.AddForce (Vector3.forward * 10);
		}
	}
}
