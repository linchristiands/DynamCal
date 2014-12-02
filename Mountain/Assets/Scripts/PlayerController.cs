using UnityEngine;
using System.Collections;

public class PlayerController : MonoBehaviour {

	public KeyCode KEY_JUMP = KeyCode.Space;

	private bool isGrounding = false;
	private int Score;
	public GUIText scoreText;
	private double Speed=1.0;
	public Transform animation;
	// Use this for initialization
	void Start () {
		Score = 0;
		scoreText.text = "Score :" + Score.ToString ();
	}


	/*void OnTriggerStay(Collider other)
	{
		Debug.Log ("Stay");
		Debug.Log (other.tag);
	}*/
	void OnCollisionStay(Collision collisionInfo) {
		//Debug.Log ("Stay");
		//Debug.Log ( collisionInfo.gameObject.tag );
		/*foreach (ContactPoint contact in collisionInfo.contacts) {
			Debug.DrawRay(contact.point, contact.normal * 100, Color.white);
		}*/
	}

	// 
	void OnCollisionEnter(Collision collision)
	//void OnTriggerEnter(Collider other)
	{
		Debug.Log (collision.gameObject.tag);

		if (collision.gameObject.tag == "Terrain") {
		
			isGrounding = true;
		}

		//Destroy(other.gameObject);
		//Destroy (gameObject);
	}

	void OnCollisionExit(Collision collision)
		//void OnTriggerEnter(Collider other)
	{
		Debug.Log (collision.gameObject.tag);
		
		if (collision.gameObject.tag == "Terrain") {
			
			isGrounding = false;
		}
		
		//Destroy(other.gameObject);
		//Destroy (gameObject);
	}

	void OnTriggerExit(Collider other)
	{
		//Debug.Log (other.tag);
		//Debug.Log ("On exit");
		if (other.tag == "Terrain") {
			isGrounding = false;
		}
		
		//Destroy(other.gameObject);
		//Destroy (gameObject);
	}

	void OnTriggerEnter(Collider c)
	{
		Debug.Log ("Enter Trigger");
		if (c.tag == "BonusStar") 
		{
			Destroy(c.gameObject);
		}
		if (c.tag == "BonusVinyl") 
		{
			Destroy (c.gameObject);
			Score+= 100;
			scoreText.text = "Score :" + Score.ToString ();
			Instantiate(animation,transform.position,transform.rotation);
		}
		if (c.tag == "Flag") 
		{
		
		}
	}
	// Update is called once per frame
	void Update ()
	{
	 // Permet de savoir si on touche le sol

		if (Input.GetKeyDown( KEY_JUMP ) )
		{
			Debug.Log ("Jump button");
			if( isGrounding )
			rigidbody.velocity += Vector3.up * 30;
		}
		if(Input.GetKeyDown(KeyCode.RightArrow))
		{
			rigidbody.velocity+= Vector3.right*2;
		}
		if(Input.GetKeyDown(KeyCode.LeftArrow))
		{
			rigidbody.velocity+= Vector3.left*2;
		}
	}
}
