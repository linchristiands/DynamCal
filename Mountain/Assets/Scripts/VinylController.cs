using UnityEngine;
using System.Collections;

public class VinylController : MonoBehaviour {

	// Use this for initialization
	void Start () {
		tag = "BonusVinyl";
	}



	// Update is called once per frame
	void Update () {
		transform.Rotate(Vector3.right * Time.deltaTime * 100);
	}
}
