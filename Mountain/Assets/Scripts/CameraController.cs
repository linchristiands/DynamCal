using UnityEngine;
using System.Collections;

public class CameraController : MonoBehaviour {

	// Variables
	public  GameObject cameraTarget;	// Object to look at or follow
	private Transform thisTransform;    // Camera Transform
	public float camZ = -10; 			// Position Z de la caméra
	public float duration = 3.0F;
	// Use this for initialization
	void Start () {
		thisTransform = transform;

		//camZ = transform.position.z;
	}
	
	// Update is called once per frame
	void Update () {
		float t = Mathf.PingPong(Time.time, duration) / duration;
		float x = cameraTarget.transform.position.x;
		float y = cameraTarget.transform.position.y;
		thisTransform.position = new Vector3 (x, y, camZ);
	}
}
