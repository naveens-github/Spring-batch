## Spring Batch
    * Using Spring batch we can create robust batch processing system.
    * Batch processing is a technique which processes data in large group instead of single element of data. 
    Where we can process high volume of data with minimal human interaction.
    
   #### when to use?
      * When ever we want to transform the large amount of data from source to destination. 
      * Eg 1: Billing analysis system:
         * So we have billing info in CSV file and we have to dump that data into the database. 
         * If we don't have this Spring Batch then we have to insert all the rows into database by writing insert statement. 
      * Eg 2: Report Generation System:
         * Every day if we want to generate a CSV or Excel report from the database.
         
#### Spring batch core components:
    * Job Launcher: This used to launch the sping batch applications. This is an entry point to any job in spring batch. 
      This has run method which triggers the job object (job component).
    * Job: Job can be defined as the work to be executed using spring batch.
    * Job Repository: This component will be called immediately once after the job launcher launches the job. 
      This Job Repository helps us to maintain the status of the job whether it succeed or failed. This will help us to rerun the jobs. 
    * Step: Job component will talk to another component of the spring batch called Step. It has another three components,
       1. ItemReader, 2. ItemProcessor, 3. ItemWriter
    * ItemReader: This will read the data from the source (Eg: CSV file is a source).
    * ItemProcessor: If we want to do any operations between Reading and Writing, that will be performed here.
    * ItemWriter: Item Writer is used to write the data to the destination (Eg: Database is destination)
    
    Note: Job can have multiple steps, and steps can have multiple ItemReader, ItemProcessor, ItemWriter.
