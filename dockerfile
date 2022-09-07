FROM dockerhub.XYZ.com/OrgName/dev/ci/agents/ubuntu-python:3.8

# Setting environement variables which can be configured to receive parameter from Jenkins Job run and pass the values to the Docker containers.
ENV artifactoryUserName="user"
ENV artifactoryPass="pass"
ENV artifactoryScanPeriod="60"

# Install pip using python
RUN python3 -m pip install --upgrade pip==22.2.2 \
  && python3 -m pip config set global.index-url https://XYZ.com/artifactory/api/pypi/foldername-python-all/simple/ \
  && python3 -m pip config set global.extra-index-url https://XYZ.com/artifactory/api/pypi/foldername-pypi/simple/
  
# Install packages thare are required on the python program, that are not available as part of python 3.8 package
RUN python3 -m pip install requests
RUN python3 -m pip install simplejson

# Push Python program inside the docker
COPY pythonfile.py /

# Run python program inside the docker
CMD [ "python3", "pythonfile.py"]
