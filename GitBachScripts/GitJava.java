import java.lang.ProcessBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;


public class GitJava{

	public GitJava(){

	}

	public String[] status(String directory){
		return runProcess(directory, "gitStatus.sh", new String[0]);
	}

	public String[] add(String directory, String[] filesToAdd){
		return runProcess(directory, "gitadd.sh", filesToAdd);
	}

	public String[] commit(String directory, String message){
		return runProcess(directory, "gitcommit.sh", new String[]{message});
	}

	public String[] remove(String directory, String[] filesToRemove){
		return runProcess(directory, "gitRemove.sh", filesToRemove);
	}

	public String[] init(String directory){
		return runProcess(directory, "gitInit.sh", new String[0]);
	}

	public void pull(){

	}

	public void push(){
		// git push [remote] [branch] pushes the branch to the remote
	}

	public void clone(){

	}

	public void remote(){

	}

	public void branch(){

	}

	public void checkout(){

	}


	public void log(){
		//git log returns information on last commits (pass -x where x is an int to only see the last x commits)
	}

	// a .gitignore file can be created and eddited to allow files to be ignored
	public void getGitIgnore(){
		
	}

	public void setGitIgnore(){

	}



	//git branch MyBranch: makes new branch MyBranch
	//git checkout MyBranch changes to MyBranch

	//git merge MyBranch: must merge from the destination branch, calling the source branch 

	//git push

	//git remote

	private String[] runProcess(String directory,String operation, String[] args){

		ArrayList<String> resultlist = new ArrayList<String>();

		// make processBuilder and set directory
		List<String> perams = new ArrayList<String>();
		perams.add(0,System.getProperty("user.dir") + "/" + operation);
		perams.add(1,directory);
		
		for(int i = 0; i < args.length; i++){
			perams.add(args[i]);
		}
		ProcessBuilder pb = new ProcessBuilder("/bin/bash");
		pb.command(perams);
		pb.directory(new File(System.getProperty("user.dir")));
		try{
			Process process = pb.start();
			//create needed objects to read output
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			try{
				while((line = br.readLine()) != null){
					resultlist.add(line);
				}
			}
			catch(Exception e){
				System.out.println("Error reading bash script output: " + e);
			}
			//Wait to get exit value
        	try {
            	int exitValue = process.waitFor();
	        } 
	        catch (InterruptedException e) {

	        }
	        
		}
		catch(Exception e){System.out.println("Error starting processBuilder: " + e);}
		
		return resultlist.toArray(new String[0]);
	}



}