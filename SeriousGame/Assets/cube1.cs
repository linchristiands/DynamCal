using UnityEngine;
using System.Collections;

public class cube1 : MonoBehaviour {


	void OnTriggerEnter(Collider c){
		if (c.tag == "finTapis") {
			Destroy(gameObject);		
		}
	}

}
