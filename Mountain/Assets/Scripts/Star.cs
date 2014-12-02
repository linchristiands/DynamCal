using UnityEngine;
using System.Collections;

public class Star : MonoBehaviour {

	public float rotSpeed = 100;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		// Slowly rotate the object around its X axis at 1 degree/second.
		transform.Rotate(Vector3.right * Time.deltaTime * rotSpeed);
	}
}
